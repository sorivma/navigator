package entity.comporator;

import entity.Route;
import struct.hashtable.HashTable;

import java.util.Comparator;

public class RouteComparator implements Comparator<Route> {
    private final String startPoint;
    private final String endPoint;
    private final HashTable<Route> hashTable;

    public RouteComparator(String startPoint, String endPoint, HashTable<Route> hashTable) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.hashTable = hashTable;
    }

    @Override
    public int compare(Route o1, Route o2) {
        if (o2.isFavorite() && !o1.isFavorite()) {
            return -1;
        }
        if (o1.isFavorite() && !o2.isFavorite()) {
            return 1;
        }
        if (countDistance(o1.getLocationPoints()) != countDistance(o2.getLocationPoints())) {
            return countDistance(o2.getLocationPoints()) - countDistance(o1.getLocationPoints());
        }
        if (o2.getPopularity() != o1.getPopularity()) {
            return o1.getPopularity() - o2.getPopularity();
        }
        return hashTable.getOrder(o2.hashCode()) - hashTable.getOrder(o1.hashCode());
    }

    private int countDistance(String[] array) {
        int startIndex = -1;
        int endIndex = -1;

        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(startPoint)) {
                startIndex = i;
                break;
            }
        }

        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(endPoint)) {
                endIndex = i;
                break;
            }
        }

        return endIndex - startIndex;
    }
}
