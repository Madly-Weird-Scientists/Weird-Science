package com.mad_scientists.weird_science.item.component.util.asset_generators;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class JsonModelDeleter {
    public static void main(String[] args) {
        String folderPath = "src/main/resources/assets/weird_science/models/item/component_variants/";

        try {
            Files.walk(Paths.get(folderPath))
                    .filter(Files::isRegularFile)
                    .filter(path -> path.getFileName().toString().matches("component_variant_[a-zA-Z0-9]+_[a-zA-Z0-9]+\\.json"))
                    .forEach(JsonModelDeleter::deleteFile);
        } catch (Exception e) {
            System.out.println("An error occurred while purging files: " + e.getMessage());
        }
    }

    private static void deleteFile(Path filePath) {
        try {
            Files.delete(filePath);
            System.out.println("Purged file: " + filePath);
        } catch (Exception e) {
            System.out.println("Failed to purge file: " + filePath);
        }
    }

}
