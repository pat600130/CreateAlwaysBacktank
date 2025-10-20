package com.example.alwaysbacktank;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Config {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final File FILE = new File("config/alwaysbacktank.json");

    public static int overlayX = 86; // default offset from center
    public static int overlayY = 44; // default offset from bottom

    public static void load() {
        if (!FILE.exists()) {
            save(); // write defaults if file missing
            return;
        }
        try (FileReader reader = new FileReader(FILE)) {
            ConfigData data = GSON.fromJson(reader, ConfigData.class);
            if (data != null) {
                overlayX = data.overlayX;
                overlayY = data.overlayY;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void save() {
        ConfigData data = new ConfigData();
        data.overlayX = overlayX;
        data.overlayY = overlayY;

        FILE.getParentFile().mkdirs();
        try (FileWriter writer = new FileWriter(FILE)) {
            GSON.toJson(data, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class ConfigData {
        int overlayX;
        int overlayY;
    }
}
