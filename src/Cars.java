import java.util.*;


abstract class Vehicle {
    protected String make;
    protected String model;
    protected int year;

    public Vehicle(String make, String model, int year) {
        this.make = make;
        this.model = model;
        this.year = year;
    }

    public abstract void start();
    public abstract void stop();

    @Override
    public String toString() {
        return year + " " + make + " " + model;
    }
}


interface Refuelable {
    void refuel();
}


class Car extends Vehicle implements Refuelable {
    public Car(String make, String model, int year) {
        super(make, model, year);
    }

    @Override
    public void start() {
        System.out.println(toString() + " запускається.");
    }

    @Override
    public void stop() {
        System.out.println(toString() + " зупиняється.");
    }

    @Override
    public void refuel() {
        System.out.println(toString() + " заправляється.");
    }
}


class Bike extends Vehicle {
    public Bike(String make, String model, int year) {
        super(make, model, year);
    }

    @Override
    public void start() {
        System.out.println(toString() + " запускається.");
    }

    @Override
    public void stop() {
        System.out.println(toString() + " зупиняється.");
    }
}


class Truck extends Vehicle implements Refuelable {
    public Truck(String make, String model, int year) {
        super(make, model, year);
    }

    @Override
    public void start() {
        System.out.println(toString() + " запускається.");
    }

    @Override
    public void stop() {
        System.out.println(toString() + " зупиняється.");
    }

    @Override
    public void refuel() {
        System.out.println(toString() + " заправляється.");
    }
}

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Vehicle> vehicles = new ArrayList<>();

        while (true) {
            System.out.println("\n--- Система управління транспортними засобами ---");
            System.out.println("1. Додати транспортний засіб");
            System.out.println("2. Переглянути транспортні засоби");
            System.out.println("3. Запустити транспортний засіб");
            System.out.println("4. Зупинити транспортний засіб");
            System.out.println("5. Заправити транспортний засіб");
            System.out.println("6. Вийти");
            System.out.print("Оберіть опцію: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.println("Оберіть тип: 1. Автомобіль  2. Мотоцикл  3. Вантажівка");
                    int type = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Введіть марку: ");
                    String make = scanner.nextLine();
                    System.out.print("Введіть модель: ");
                    String model = scanner.nextLine();
                    System.out.print("Введіть рік: ");
                    int year = scanner.nextInt();

                    switch (type) {
                        case 1 -> vehicles.add(new Car(make, model, year));
                        case 2 -> vehicles.add(new Bike(make, model, year));
                        case 3 -> vehicles.add(new Truck(make, model, year));
                        default -> System.out.println("Неправильний тип.");
                    }
                }
                case 2 -> {
                    if (vehicles.isEmpty()) {
                        System.out.println("Немає доступних транспортних засобів.");
                    } else {
                        System.out.println("--- Список транспортних засобів ---");
                        for (int i = 0; i < vehicles.size(); i++) {
                            System.out.println((i + 1) + ". " + vehicles.get(i));
                        }
                    }
                }
                case 3 -> {
                    System.out.print("Введіть номер транспортного засобу для запуску: ");
                    int index = scanner.nextInt() - 1;
                    if (isValidIndex(index, vehicles)) {
                        vehicles.get(index).start();
                    }
                }
                case 4 -> {
                    System.out.print("Введіть номер транспортного засобу для зупинки: ");
                    int index = scanner.nextInt() - 1;
                    if (isValidIndex(index, vehicles)) {
                        vehicles.get(index).stop();
                    }
                }
                case 5 -> {
                    System.out.print("Введіть номер транспортного засобу для заправки: ");
                    int index = scanner.nextInt() - 1;
                    if (isValidIndex(index, vehicles) && vehicles.get(index) instanceof Refuelable) {
                        ((Refuelable) vehicles.get(index)).refuel();
                    } else {
                        System.out.println("Цей транспортний засіб не можна заправити.");
                    }
                }
                case 6 -> {
                    System.out.println("Вихід...");
                    return;
                }
                default -> System.out.println("Неправильна опція. Спробуйте ще раз.");
            }
        }
    }

    private static boolean isValidIndex(int index, List<Vehicle> vehicles) {
        if (index >= 0 && index < vehicles.size()) {
            return true;
        } else {
            System.out.println("Неправильний номер транспортного засобу.");
            return false;
        }
    }
}
