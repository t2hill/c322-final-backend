package edu.iu.t2hill.c322finalbackend.repository;

import edu.iu.t2hill.c322finalbackend.model.Flower;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

@Repository
public class FlowerRepository {

    private final String IMAGES_FOLDER_PATH = "data/flowers/";
    private static final String NEW_LINE = System.lineSeparator();
    private static final String DATABASE_NAME = "data/db.txt";

    private static void appendToFile(Path path, String content) throws IOException {
        Files.write(path,
                content.getBytes(StandardCharsets.UTF_8),
                StandardOpenOption.CREATE,
                StandardOpenOption.APPEND);
    }

    public List<Flower> findAll() throws IOException {
        List<Flower> result = new ArrayList<>();
        Path path = Paths.get(DATABASE_NAME);
        if (Files.exists(path)) {
            List<String> data = Files.readAllLines(path);
            for (String line : data) {
                if(line.trim().length() != 0) {
                    Flower f = Flower.fromLine(line);
                    result.add(f);
                }
            }
        }
        return result;
    }

    public Flower find(int id) throws IOException {
        List<Flower> flowers = findAll();
        for(Flower flower : flowers) {
            if (flower.getId() == id) {
                return flower;
            }
        }
        return null;
    }

    public byte[] getImage(int id) throws IOException {
        String fileExtension = ".jpg";
        Path path = Paths.get(IMAGES_FOLDER_PATH
                + id + fileExtension);
        byte[] image = Files.readAllBytes(path);
        return image;
    }
}
