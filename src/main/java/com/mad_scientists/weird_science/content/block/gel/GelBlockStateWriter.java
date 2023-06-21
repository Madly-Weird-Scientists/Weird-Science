package com.mad_scientists.weird_science.content.block.gel;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class GelBlockStateWriter {
    public static void main(String[] args) {
        String baseModel = "weird_science:block/gel";
        String gelTypesFilePath = "src/main/resources/assets/weird_science/modelGenerators/gelTypes.txt";
        String outputFolderPath = "src/main/resources/assets/weird_science/gelModels/";

        try {
            BufferedReader reader = new BufferedReader(new FileReader(gelTypesFilePath));
            String line = reader.readLine();
            if (line != null) {
                String gelType = line.trim();
                String outputFileName = gelType + "_gel.json";
                String outputFilePath = outputFolderPath + outputFileName;

                JsonObject json = new JsonObject();
                JsonObject textures = new JsonObject();

                textures.addProperty("gel", "weird_science:block/gel/" + gelType);
                textures.addProperty("particle", "weird_science:block/gel/" + gelType);

                json.addProperty("parent", baseModel);
                json.add("textures", textures);

                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                String jsonString = gson.toJson(json);

                FileWriter fileWriter = new FileWriter(outputFilePath);
                fileWriter.write(jsonString);
                fileWriter.close();

                System.out.println("Generated JSON file: " + outputFilePath);
            } else {
                System.out.println("No gel type found in the file.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while generating JSON file: " + e.getMessage());
        }
    }
}
