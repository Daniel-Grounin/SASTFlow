package com.sast;

import java.io.*;
import java.nio.file.*;

public class FileScanner {
    public static String scanFile(String filePath) throws IOException {
        System.out.println("🔍 Scanning file: " + filePath);
        StringBuilder fileContent = new StringBuilder();

        for (String line : Files.readAllLines(Paths.get(filePath))) {
            fileContent.append(line).append("\n");
        }

        return fileContent.toString();
    }
}
