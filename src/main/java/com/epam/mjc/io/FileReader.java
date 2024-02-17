package com.epam.mjc.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;


public class FileReader {

    public Profile getDataFromFile(File file) throws IOException {
        StringBuilder content = new StringBuilder();

        try (BufferedReader br = new BufferedReader(new java.io.FileReader(file.getAbsolutePath()))) {
            String line;
            while ((line = br.readLine()) != null) {
                content.append(line);
                content.append(" ");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        String[] splitContent = content.toString().trim().split(" ");

        HashMap<String, String> profileData = new HashMap<>();

        for (int i = 0; i < splitContent.length; i += 2) {
            String key = splitContent[i].replace(":", "");
            String value = splitContent[i + 1];
            profileData.put(key, value);
        }

        return new Profile(profileData.get("Name"), Integer.valueOf(profileData.get("Age")),
                profileData.get("Email"), Long.valueOf(profileData.get("Phone"))
        );
    }
}
