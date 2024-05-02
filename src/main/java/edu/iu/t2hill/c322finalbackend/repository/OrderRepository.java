package edu.iu.t2hill.c322finalbackend.repository;

import edu.iu.t2hill.c322finalbackend.model.Flower;
import edu.iu.t2hill.c322finalbackend.model.Receipt;
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
public class OrderRepository {

    private static final String ORDERS_DATABASE_NAME = "data/receipts.txt";
    private static final String NEW_LINE = System.lineSeparator();

    private static void appendToFile(Path path, String content) throws IOException {
        Files.write(path,
                content.getBytes(StandardCharsets.UTF_8),
                StandardOpenOption.CREATE,
                StandardOpenOption.APPEND);
    }

    public int addOrder(Receipt receipt) throws IOException {
        Path path = Paths.get(ORDERS_DATABASE_NAME);
        String data = receipt.toLine();
        appendToFile(path, data + NEW_LINE);
        return receipt.id();
    }

    public List<Receipt> findAll() throws IOException {
        List<Receipt> result = new ArrayList<>();
        Path path = Paths.get(ORDERS_DATABASE_NAME);
        if (Files.exists(path)) {
            List<String> data = Files.readAllLines(path);
            for (String line : data) {
                if(line.trim().length() != 0) {
                    Receipt r = Receipt.fromLine(line);
                    result.add(r);
                }
            }
        }
        return result;
    }

    public Receipt find(int id) throws IOException {
        List<Receipt> receipts = findAll();
        for(Receipt receipt : receipts) {
            if (receipt.id() == id) {
                return receipt;
            }
        }
        return null;
    }


}
