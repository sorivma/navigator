import entity.Route;
import navigator.impl.HashTableNavigator;
import util.RouteGenerator;

import java.util.Scanner;
import java.util.UUID;

public class Main {
    private static final HashTableNavigator navigator = new HashTableNavigator();
    private static final Scanner scanner = new Scanner(System.in);
    private static boolean isRunning = true;
    private static final int initialSize = 10000;


    public static void main(String[] args) {
        seedData(initialSize);

        do {
            int choice = mainMenu();
            switch (choice) {
                case 1 -> routeList();
                case 2 -> createRoute();
                case 3 -> deleteRoute();
                case 4 -> searchById();
                case 5 -> findByStartAndEnd();
                case 6 -> chooseRote();
                case 7 -> favoriteRoutes();
                case 8 -> top5();
                case 9 -> isRunning = false;
            }

        } while (isRunning);
    }

    private static void top5() {
        System.out.println("Топ 5 маршрутов");
        for (Route route : navigator.getTop3Routes()) {
            System.out.println(route);
        }
    }

    private static void favoriteRoutes() {
        System.out.println("""
        
                1. Просмотреть
                2. Добавить
        
                """);

        System.out.print("Введите вариант: ");
        switch (scanner.nextInt()) {
            case 1 -> showFavorite();
            case 2 -> addFavorite();
        }
    }

    private static void addFavorite() {
        System.out.print("Введите id маршрута: ");
        String id = scanner.next();
        Route route = navigator.getRoute(id);
        route.setFavorite(true);
        navigator.addRoute(route);
        System.out.println("Избранный маршрут добавлен");
        System.out.println(route);
    }

    private static void showFavorite() {
        System.out.print("Введите точку назначения: ");
        String destination = scanner.next();

        System.out.println("Избранные маршруты");
        for (Route favorite : navigator.getFavoriteRoutes(destination)) {
            System.out.println(favorite);
        }
    }

    private static void findByStartAndEnd() {
        System.out.print("Введите начальную точку маршрута: ");
        String startPoint = scanner.next();
        System.out.print("Введите конечную точку маршрута: ");
        String endPoint = scanner.next();
        System.out.println("Найденные маршруты");
        Iterable<Route> routes = navigator.searchRoutes(startPoint, endPoint);
        for (Route route : routes) {
            System.out.println(route);
        }
    }

    private static void searchById() {
        System.out.println("Введите нужную информацию");
        System.out.print("Введите id: ");
        String id = scanner.next();

        Route route = new Route();
        route.setId(id);

        boolean contains = navigator.contains(route);

        if (!contains) {
            System.out.println("Маршрут не найден");
            return;
        }

        System.out.println("Маршрут найден");
        System.out.println(navigator.getRoute(id));
    }

    private static void chooseRote() {
        System.out.print("Введите id маршрута: ");
        String id = scanner.next();
        navigator.chooseRoute(id);
    }

    private static void deleteRoute() {
        System.out.print("Введите id маршрута для удаленя: ");
        navigator.removeRoute(scanner.next());
    }

    private static void createRoute() {
        System.out.print("Введите расстояние маршрута: ");
        double distance = scanner.nextDouble();

        System.out.print("Введите количество точек маршрута: ");
        int size = scanner.nextInt();

        String[] locationPoints = new String[size];
        for (int i = 0; i < size; i++) {
            int number = i + 1;
            System.out.print("Введите название " + number + "-го города: ");
            locationPoints[i] = scanner.next();
        }

        Route route = new Route(
                distance,
                0,
                false,
                locationPoints
        );

        navigator.addRoute(route);

        System.out.println("Создан следующий маршрут:");
        System.out.println(route);
    }

    private static void seedData(int size) {
        Route[] routes = RouteGenerator.generate(size);
        for (Route route : routes) {
            navigator.addRoute(route);
        }
    }

    private static int mainMenu() {
        String menu =
                """
                        Добро пожаловать в навигатор
                        1. Количество маршрутов
                        2. Создание маршрута
                        3. Удаление маршрута
                        4. Поиск маршрута по ID
                        5. Поиск маршрута по начальной и конечной точке
                        6. Выбор маршрута.
                        7. Любимые маршруты
                        8. Топ 5 маршрутов
                        9. Выход
                        """;

        System.out.println(menu);
        System.out.print("Выберите пункт: ");

        return scanner.nextInt();
    }

    private static void routeList() {
        System.out.println("Общее количество маршрутов: " + navigator.size());
    }
}