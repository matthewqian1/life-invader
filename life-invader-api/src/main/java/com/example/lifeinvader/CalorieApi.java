package com.example.lifeinvader;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CalorieApi {

    public static void main(String[] args) throws Exception {
        URL url = new URL("https://api.api-ninjas.com/v1/nutrition?query=fries");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestProperty("accept", "application/json");
        connection.setRequestProperty("X-Api-Key", "BmkEi8jF05eXjSoaizJOCQ==V0ddaLo63EAS7aTF");

        InputStream responseStream = connection.getInputStream();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(responseStream);
        System.out.println(root.path("fact").asText());
    }


}
