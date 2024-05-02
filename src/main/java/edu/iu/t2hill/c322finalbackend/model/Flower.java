package edu.iu.t2hill.c322finalbackend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(schema = "flowers")
public class Flower {
    @Id
    private Integer id;
    private String name;
    private Float cost;
    private String type;
    private String occasion;
    private String color;

    public Flower() {}

    public Flower(Integer id, String name, Float cost, String type, String occasion, String color) {
        this.id = id;
        this.name = name;
        this.cost = cost;
        this.type = type;
        this.occasion = occasion;
        this.color = color;
    }

    public String toLine() {
        String line = String.format("%1s,%2s,%3s,%4s,%5s,%6s",
                getId(),
                getName().trim(),
                getCost(),
                getType().trim(),
                getOccasion().trim(),
                getColor().trim());
        return line;
    }

    public static Flower fromLine(String line) {
        String[] tokens = line.split(",");
        Flower flower = new Flower(Integer.parseInt(tokens[0]),
                tokens[1].trim(),
                Float.parseFloat(tokens[2]),
                tokens[3].trim(),
                tokens[4].trim(),
                tokens[5].trim());
        return flower;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getCost() {
        return cost;
    }

    public void setCost(Float cost) {
        this.cost = cost;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOccasion() {
        return occasion;
    }

    public void setOccasion(String occasion) {
        this.occasion = occasion;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
