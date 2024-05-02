package edu.iu.t2hill.c322finalbackend.model;

public record Receipt(int id, float cost, String firstName, String lastName, String status) {

    public String toLine() {
        return String.format("%1$s,%2$s,%3$s,%4$s,%5$s", id(), cost(), firstName(), lastName(), status());
    }

    public static Receipt fromLine(String line) {
        String[] tokens = line.split(",");
        return new Receipt(Integer.parseInt(tokens[0]), Float.parseFloat(tokens[1]), tokens[2], tokens[3], tokens[4]);
    }

}
