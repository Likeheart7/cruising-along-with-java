package space.iss.info;

import com.fasterxml.jackson.databind.ObjectMapper;
import space.Location;

import java.net.URI;
import java.util.Map;
import java.util.Scanner;

public class ISSLocation {
    public Location lookupLocation() {
//        ObjectMapper om = new ObjectMapper();
        var ISS_NOW_URL = "http://api.open-notify.org/iss-now.json";
        try (var scanner = new Scanner(URI.create(ISS_NOW_URL).toURL().openStream())) {
            String responseText = scanner.nextLine();
            return extractLocationFrom(responseText);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @SuppressWarnings("unchecked")
    private Location extractLocationFrom(String text) {
        try {
            ObjectMapper om = new ObjectMapper();
            var data = om.readValue(text, Map.class);
            Map<String, String> location = (Map<String, String>) data.get("iss_position");
            return new Location(
                Double.parseDouble(location.get("latitude")),
                Double.parseDouble(location.get("longitude"))
            );
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
