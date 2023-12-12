package com.jasonConventor;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

public class FlattenJson {
    public static void main(String[] args) {
        try {
            // Read the JSON file
            JSONArray jsonArray = new JSONArray(readFile("OTO_capital_1.json"));

            // Flatten the JSON array
            JSONArray flattenedArray = flattenJsonArray(jsonArray);

            // Write the flattened data to a new JSON file
            try (FileWriter fileWriter = new FileWriter("Combine_OTO_capital.json")) {
                fileWriter.write(flattenedArray.toString(2));
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }

    private static String readFile(String filePath) throws IOException {
        StringBuilder content = new StringBuilder();
        try (FileReader fileReader = new FileReader(filePath)) {
            int c;
            while ((c = fileReader.read()) != -1) {
                content.append((char) c);
            }
        }
        return content.toString();
    }


    private static JSONArray flattenJsonArray(JSONArray jsonArray) throws JSONException {
        JSONArray flattenedArray = new JSONArray();

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject flattenedObject = flattenJsonObject(jsonArray.getJSONObject(i));
            flattenedArray.put(flattenedObject);
        }

        return flattenedArray;
    }

    private static JSONObject flattenJsonObject(JSONObject jsonObject) throws JSONException {
        JSONObject flattenedObject = new JSONObject();

        flattenJsonObject("", jsonObject, flattenedObject);

        return flattenedObject;
    }

    private static void flattenJsonObject(String currentKey, JSONObject jsonObject, JSONObject flattenedObject) throws JSONException {
        Iterator<String> keys = jsonObject.keys();

        while (keys.hasNext()) {
            String key = keys.next();
            String newKey = currentKey.isEmpty() ? key : currentKey + "_" + key;

            if (jsonObject.get(key) instanceof JSONObject) {
                flattenJsonObject(newKey, jsonObject.getJSONObject(key), flattenedObject);
            } else {
                flattenedObject.put(newKey, jsonObject.get(key));
            }
        }
    }
}

