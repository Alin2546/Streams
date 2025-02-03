package org.example;


import lombok.NoArgsConstructor;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 * BirthdaySoftware used for displaying persons birthdays on an indicated month
 */

@NoArgsConstructor
public class BirthdaySoftware {
     Set<Person> personSet = new TreeSet<>(Comparator.comparing(Person::getFirstName));


    /**
     * @param inputFile  input File
     * @param month      target month
     * @param outputFile output File
     */
    BirthdaySoftware(String inputFile, String month, String outputFile) {
        readFile(inputFile);
        writeToFile(outputFile, month);
    }

    /**
     * @param fileName the file we read from
     */
    void readFile(String fileName) {
        Path path = Paths.get(fileName);
        try {
            List<String> list = Files.readAllLines(path, StandardCharsets.UTF_8);
            list.forEach(s -> {
                String[] compose = s.split(",");
                if (compose.length != 3) {
                    System.out.println("Csv file corrupted, cannot read line");
                } else
                    personSet.add(new Person(compose[0], compose[1], compose[2]));
            });
        } catch (IOException e) {
            System.out.println("File name: " + e.getMessage() + "(Corrupted)");
        }
    }

    /**
     * @param fileName the file we write to
     * @param month    target month
     */
    void writeToFile(String fileName, String month) {
        Path path = Paths.get(fileName);
        List<String> list = new LinkedList<>();
        personSet.forEach(person -> {
            if (person.getDate() != null && person.getDate().length() == 10) {
                if (month.equals(person.getDate().substring(5, 7))) {
                    String line = person.getFirstName() + "," + person.getLastName();
                    list.add(line);
                }
            } else {
                System.out.println("Person: " + person.getFirstName() + " " + person.getLastName() + " has invalid age");
            }
        });
        try {
            Files.write(path, list);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
