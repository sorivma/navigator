package navigator.impl;

import entity.Route;
import entity.comporator.FavoriteComparator;
import entity.comporator.RouteComparator;
import entity.comporator.TopComparator;
import navigator.Navigator;
import struct.ArrayList;
import struct.hashtable.HashTable;

import java.util.Comparator;

public class HashTableNavigator implements Navigator {
    private final HashTable<Route> hashTable = new HashTable<>();

    @Override
    public void addRoute(Route route) {
        hashTable.add(route);
    }

    @Override
    public void removeRoute(String routeId) {
        hashTable.remove(routeId.hashCode());
    }

    @Override
    public boolean contains(Route route) {
        return hashTable.containsKey(route.hashCode());
    }

    @Override
    public int size() {
        return hashTable.size();
    }

    @Override
    public Route getRoute(String routeId) {
        return hashTable.get(routeId.hashCode());
    }

    @Override
    public void chooseRoute(String routeId) {
        Route route = hashTable.get(routeId.hashCode());
        route.setPopularity(route.getPopularity() + 1);
        hashTable.add(route);
    }

    @Override
    public Iterable<Route> searchRoutes(String startPoint, String endPoint) {
        ArrayList<Route> matchingRoutes = new ArrayList<>();

        for (Route route: hashTable.values()) {
            if (hasLogicalMeaning(route, startPoint, endPoint)) {
                matchingRoutes.add(route);
            }
        }

        Comparator<Route> routeComparator = new RouteComparator(startPoint, endPoint, hashTable);
        matchingRoutes.sort(routeComparator.reversed());

        return matchingRoutes;
    }

    private boolean hasLogicalMeaning(Route route, String startPoint, String endPoint) {
        String[] locationPoints = route.getLocationPoints();

        int startIndex = -1;
        int endIndex = -1;

        for (int i = 0; i < locationPoints.length; i++) {
            if (locationPoints[i].equals(startPoint)) {
                startIndex = i;
            } else if (locationPoints[i].equals(endPoint)) {
                endIndex = i;
            }
        }

        return startIndex != -1 && endIndex != -1 && startIndex < endIndex;
    }

    @Override
    public Iterable<Route> getFavoriteRoutes(String destinationPoint) {
        ArrayList<Route> routes = new ArrayList<>();
        for (Route route : hashTable.values()) {
            if (route.isFavorite() && containsDestination(destinationPoint, route)) {
                routes.add(route);
            }
        }

        Comparator<Route> comparator = new FavoriteComparator(hashTable);
        routes.sort(comparator);

        return routes;
    }

    private boolean containsDestination(String destinationPoint, Route route) {
        for (int i = 1; i < route.getLocationPoints().length; i++) {
            if (route.getLocationPoints()[i].equals(destinationPoint)) return true;
        }
        return false;
    }

    @Override
    public Iterable<Route> getTop3Routes() {
        ArrayList<Route> values = new ArrayList<>();
        values.addAll(hashTable.values());


        TopComparator topComparator = new TopComparator(hashTable);
        values.sort(topComparator);

        ArrayList<Route> top5 = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            top5.add(values.get(i));
        }

        return top5;
    }
}
