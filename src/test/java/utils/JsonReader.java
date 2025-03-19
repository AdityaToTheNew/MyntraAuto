package utils;

import org.json.JSONArray;
import org.json.JSONObject;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class JsonReader {

    public static List<String> getProductsFromJson(String filePath) {
        List<String> products = new ArrayList<>();

        try (FileReader reader = new FileReader(filePath)) {
            // Read JSON file
            char[] buffer = new char[1024];
            int bytesRead = reader.read(buffer);
            String jsonData = new String(buffer, 0, bytesRead);

            // Parse JSON
            JSONObject jsonObject = new JSONObject(jsonData);
            JSONArray jsonArray = jsonObject.getJSONArray("products");

            // Add products to the list
            for (int i = 0; i < jsonArray.length(); i++) {
                products.add(jsonArray.getString(i));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return products;
    }
}
