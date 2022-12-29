
package enterprise.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class JsonTools {

    private String readAll(BufferedReader bufferedReader) {

        StringBuilder stringBuilder = new StringBuilder();

        int charIndex;

        try {

            while ((charIndex = bufferedReader.read()) != -1) {
                stringBuilder.append((char) charIndex);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return stringBuilder.toString();
    }

    public boolean urlIsNull(String url) {

        boolean urlIsNull;

        try {

            InputStream inputStream = new URL(url).openStream();
            BufferedReader bufferedReader =
                    new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
            String urlData = readAll(bufferedReader);

            if (urlData.isBlank()) {
                return urlIsNull = true;
            }

            inputStream.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return false;
    }

    public JSONObject createJsonObjectFromUrl(String jsonUrl) {

        JSONObject jsonObject = new JSONObject();

        try {

            InputStream inputStream = new URL(jsonUrl).openStream();
            BufferedReader bufferedReader =
                    new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
            String jsonStr = readAll(bufferedReader);
            jsonObject = new JSONObject(jsonStr);
            inputStream.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return jsonObject;
    }

    public JSONArray createJsonArrayFromUrl(String jsonUrl) {

        JSONArray jsonArray = new JSONArray();

        try {

            InputStream inputStream = new URL(jsonUrl).openStream();
            BufferedReader bufferedReader =
                    new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
            String jsonStr = readAll(bufferedReader);
            jsonArray = new JSONArray(jsonStr);
            inputStream.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return jsonArray;
    }

    public String jsonFileToString(String jsonFile) {

        StringBuilder builder = new StringBuilder();

        try {

            List<String> stringLines = Files.readAllLines(Paths.get(jsonFile));
            stringLines.forEach(builder::append);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return builder.toString();
    }

    public Person convertToPersonFromJsonUrl(String personJsonUrl) {

        ObjectMapper mapper = new ObjectMapper();
        Person person = new Person();

        try {
            person = mapper.readValue(new URL(personJsonUrl), Person.class);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return person;
    }

    public List<Person> convertToPeopleListFromJsonUrl(String peopleJsonUrl) {

        ObjectMapper objectMapper = new ObjectMapper();
        List<Person> peopleList = new ArrayList<>();

        try {

            Person[] peopleArray = objectMapper.readValue(new URL(peopleJsonUrl), Person[].class);
            peopleList = List.of(peopleArray);

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return peopleList;
    }

    public String convertPersonToJsonStr(Person person) {

        ObjectMapper mapper = new ObjectMapper();
        String jsonStr = "";

        try {
            jsonStr = mapper.writeValueAsString(person);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return jsonStr;
    }

    public String convertPeopleListToJsonStr(List<Person> peopleList) {

        ObjectMapper mapper = new ObjectMapper();
        String jsonStr = "";

        try {
            jsonStr = mapper.writeValueAsString(peopleList);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return jsonStr;
    }
}
