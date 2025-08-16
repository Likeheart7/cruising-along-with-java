package space.client;

import space.Location;
import space.SpaceStationInfo;
import space.iss.ISSSpaceStation;

import java.util.Scanner;

public class SpaceClient {
    public static void main(String[] args) {
        System.out.println("Please enter the space station you are interested in:");
        try (var scanner = new Scanner(System.in)) {
            var spaceStationName = scanner.nextLine();
            if (!spaceStationName.equals("ISS")) {
                System.out.printf("Space station with name %s not found.\n", spaceStationName);
            } else {
                ISSSpaceStation spaceStation = new ISSSpaceStation();
                SpaceStationInfo spaceStationInfo = spaceStation.lookup();
                Location location = spaceStationInfo.location();
                System.out.println("Current latitude and longitude of %s: (%g, %g)".formatted(
                    spaceStationName,
                    location.latitude(),
                    location.longitude()));
                System.out.println("Current occupants of %s: %s".formatted(
                    spaceStationName,
                    String.join(", ", spaceStationInfo.occupants())));
            }
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }
}
