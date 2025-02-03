package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static junit.framework.Assert.assertEquals;

public class BirthdaySoftwareWriteTest {
    Path path;
    BirthdaySoftware birthdaySoftware;


    @BeforeEach
    void setUp() throws IOException {
        path = Files.createTempFile("test", ".csv");
        birthdaySoftware = new BirthdaySoftware();
    }

    @Test
    void writeFileNoInput() throws IOException {
        birthdaySoftware.writeToFile(path.toString(), "10");
        List<String> lines = Files.readAllLines(path);
        assertEquals(0, lines.size());
    }

    @Test
    void writeFileOneInput() throws IOException {
        birthdaySoftware.personSet.add(new Person("Ion", "Ion", "2000-07-23"));
        birthdaySoftware.writeToFile(path.toString(), "07");
        List<String> lines = Files.readAllLines(path);
        assertEquals(1, lines.size());
    }

    @Test
    void writeFileManyInputs() throws IOException {
        birthdaySoftware.personSet.add(new Person("Ion", "Ion", "2000-07-23"));
        birthdaySoftware.personSet.add(new Person("Grigore", "Grigore", "1900-07-11"));
        birthdaySoftware.writeToFile(path.toString(), "07");
        List<String> lines = Files.readAllLines(path);
        assertEquals(2, lines.size());
    }

    @Test
    void writeFileWrongInput() throws IOException{
        birthdaySoftware.personSet.add(new Person("Ion", "Ion", "2000-0-23"));
        birthdaySoftware.writeToFile(path.toString(), "07");
        List<String> lines = Files.readAllLines(path);
        assertEquals(0, lines.size());
    }
}
