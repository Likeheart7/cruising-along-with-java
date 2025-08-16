package space.iss.info;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ISSPeople {
    public List<String> lookupPeople() {
        var url = "http://api.open-notify.org/astros.json";
        try (var scanner = new Scanner(URI.create(url).toURL().openStream())) {
            String responseText = scanner.nextLine();
            return extractNamesFrom(responseText);
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }

    @SuppressWarnings("unchecked")
    private List<String> extractNamesFrom(String text) {
        try {
            ObjectMapper om = new ObjectMapper();
            var data = om.readValue(text, Map.class);
            var people = (List<Map<String, String>>) data.get("people");
            return people.stream()
                .filter(craftAndPerson -> craftAndPerson.get("craft").equals("ISS"))
                .map(craftAndPerson -> craftAndPerson.get("name"))
                .toList();
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }

    }
}
