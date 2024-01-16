package entity;

import util.Utils;

public class Route {
    private String id;
    private Double distance;
    private int popularity;
    private boolean isFavorite;
    private String[] locationPoints;

    public Route(Double distance, int popularity, boolean isFavorite, String[] locationPoints) {
        this.distance = distance;
        this.popularity = popularity;
        this.isFavorite = isFavorite;
        this.locationPoints = locationPoints;
        this.id = distance + locationPoints[0] + locationPoints[locationPoints.length - 1];
    }


    public Route() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public int getPopularity() {
        return popularity;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public String[] getLocationPoints() {
        return locationPoints;
    }

    public void setLocationPoints(String[] locationPoints) {
        this.locationPoints = locationPoints;
    }

    @Override
    public boolean equals(Object o) {
        if (o.getClass() != getClass()) return false;
        Route route = (Route) o;
        return route.distance.equals(distance)
                && (route.locationPoints[0].equals(locationPoints[0]))
                && route.locationPoints[route.locationPoints.length - 1]
                .equals(locationPoints[locationPoints.length - 1]);
    }


    @Override
    public int hashCode() {
        return id.hashCode();
    }


    @Override
    public String toString() {
        String template = """
                                
                ================
                Маршрут %s-%s,
                ID: %s,
                Дистанция: %s,
                Популярность: %s,
                Избранный: %s,
                Схема маршрута:
                %s
                ================
                                
                """;

        return String.format(template,
                locationPoints[0],
                locationPoints[locationPoints.length - 1],
                id,
                distance,
                popularity,
                isFavorite ? "Да" : "Нет",
                Utils.joinStrings(locationPoints));
    }


}
