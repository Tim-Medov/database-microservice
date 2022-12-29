
package enterprise.rest;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class Operations {

    private JsonTools jsonTools;

    public Operations() {
        this.jsonTools = new JsonTools();
    }

    public List<Person> getAll() {

        String peopleJsonUrl = "http://localhost:8080/people-db-service/people";

        if (jsonTools.convertToPeopleListFromJsonUrl(peopleJsonUrl).isEmpty()) {

            return null;

        } else {

            return jsonTools.convertToPeopleListFromJsonUrl(peopleJsonUrl);
        }
    }

    public Person getByName(String name) {

        String personJsonUrl = "http://localhost:8080/people-db-service/people/" + name;

        if (jsonTools.urlIsNull(personJsonUrl)) {

            return null;

        } else {

            return jsonTools.convertToPersonFromJsonUrl(personJsonUrl);
        }
    }

    public void add(Person person) {

        try {

            URL url = new URL("http://localhost:8080/people-db-service/people");

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("POST");

            connection.setRequestProperty("Content-Type", "application/json");

            connection.setRequestProperty("Accept", "application/json");

            connection.setDoOutput(true);

            String jsonInputString = jsonTools.convertPersonToJsonStr(person);

            byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);

            OutputStream outputStream = connection.getOutputStream();

            outputStream.write(input, 0, input.length);

            connection.getResponseCode();

            connection.disconnect();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void delete(String name) {

        String username = name.toLowerCase();

        try {

            URL url = new URL("http://localhost:8080/people-db-service/people/" + username);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("DELETE");

            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            connection.setDoOutput(true);

            String urlStr = "http://localhost:8080/people-db-service/people/" + username;

            byte[] input = urlStr.getBytes(StandardCharsets.UTF_8);

            OutputStream outputStream = connection.getOutputStream();

            outputStream.write(input, 0, input.length);

            connection.getResponseCode();

            connection.disconnect();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
