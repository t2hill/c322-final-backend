package edu.iu.t2hill.c322finalbackend.controllers;

import edu.iu.t2hill.c322finalbackend.model.Flower;
import edu.iu.t2hill.c322finalbackend.repository.FlowerRepository;
import edu.iu.t2hill.c322finalbackend.repository.OrderRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/flowers")
public class FlowerController {

    private FlowerRepository flowerRepository;

    public FlowerController(FlowerRepository flowerRepository) {
        this.flowerRepository = flowerRepository;
    }

    @GetMapping
    public List<Flower> findAll() {
        try {
            return flowerRepository.findAll();
        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Flower> find(@PathVariable int id) {
        try {
            Flower flower = flowerRepository.find(id);
            if(flower != null) {
                return ResponseEntity
                        .status(HttpStatus.FOUND)
                        .body(flower);
            } else {
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(null);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/{id}/image")
    public ResponseEntity<?> getImage(@PathVariable int id) {
        try {
            byte[] image = flowerRepository.getImage(id);
            return ResponseEntity.status(HttpStatus.FOUND)
                    .contentType(MediaType.IMAGE_PNG)
                    .body(image);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
