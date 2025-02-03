package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class BirthdaySoftwareReadTest {
    Path path;
    BirthdaySoftware birthdaySoftware;


    @BeforeEach
    void setUp() throws IOException {
        path = Files.createTempFile("test", ".csv");
        birthdaySoftware = new BirthdaySoftware();
    }

    @Test
    void ReadFileNoInput() {
        birthdaySoftware.readFile("test.csv");
        assertEquals(0, birthdaySoftware.personSet.size());
    }

    @Test
    void readFileOneInput() throws IOException {
        String line = "Alex,Alex,2025-10-10";
        Files.write(path, line.getBytes());
        birthdaySoftware.readFile(path.toString());
        assertEquals(1, birthdaySoftware.personSet.size());
    }

    @Test
    void readFileManyInputs() throws IOException {
        String line = "Bogdan,Bogdan,1999-02-02\nDragos,Dragos,2000-10-20";
        Files.write(path, line.getBytes());
        birthdaySoftware.readFile(path.toString());
        assertEquals(2, birthdaySoftware.personSet.size());
    }

    @Test
    void readFileWrongInput() throws IOException {
        String line = "Dobre,Dobre";
        Files.write(path, line.getBytes());
        birthdaySoftware.readFile(path.toString());
        assertEquals(0, birthdaySoftware.personSet.size());
    }
}