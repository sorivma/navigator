package util;

import entity.Route;

import java.security.SecureRandom;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.UUID;

public class RouteGenerator {
    private static final SecureRandom random = new SecureRandom();

    public static Route[] generate(int number) {
        Route[] routes = new Route[number];

        for (int i = 0; i < number; i++) {
            double distance = 5.0 + random.nextDouble()*10;

            String[] locationPoints = generateLocationPoints();
            distance*=locationPoints.length;

            DecimalFormat decimalFormat = new DecimalFormat("#.#");
            distance = Double.parseDouble(decimalFormat.format(distance).replace(",", "."));

            Route route = new Route(
                    distance,
                    random.nextInt(10),
                    random.nextInt(10) > 8,
                    locationPoints
            );
            routes[i] = route;
        }

        return routes;
    }

    private static String[] generateLocationPoints() {
        int arraySize = random.nextInt(5) + 2;
        String[] locationPoints = new String[arraySize];

        for (int i = 0; i < arraySize; i++) {
            locationPoints[i] = generateCityName();
        }

        return locationPoints;
    }

    private static String generateCityName() {
        return Constants.cities[random.nextInt(Constants.cities.length)];
    }
}
