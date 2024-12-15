import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;


abstract class Media {
    private String title;

    public Media(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "Назва: " + title;
    }
}


class Book extends Media {
    private String author;

    public Book(String title, String author) {
        super(title);
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    @Override
    public String toString() {
        return super.toString() + ", Автор: " + author;
    }
}


class Magazine extends Media {
    private String issue;

    public Magazine(String title, String issue) {
        super(title);
        this.issue = issue;
    }

    public String getIssue() {
        return issue;
    }

    @Override
    public String toString() {
        return super.toString() + ", Випуск: " + issue;
    }
}


class DVD extends Media {
    private int duration;

    public DVD(String title, int duration) {
        super(title);
        this.duration = duration;
    }

    public int getDuration() {
        return duration;
    }

    @Override
    public String toString() {
        return super.toString() + ", Тривалість: " + duration + " хв.";
    }
}


class Library<T extends Media> {
    private List<T> items;

    public Library() {
        items = new ArrayList<>();
    }

    public void addItem(T item) {
        items.add(item);
    }

    public void removeItem(String title) {
        items.removeIf(item -> item.getTitle().equalsIgnoreCase(title));
    }

    public List<T> searchByTitle(String title) {
        return items.stream()
                .filter(item -> item.getTitle().toLowerCase().contains(title.toLowerCase()))
                .collect(Collectors.toList());
    }

    public void displayAll() {
        if (items.isEmpty()) {
            System.out.println("Бібліотека порожня.");
        } else {
            items.forEach(System.out::println);
        }
    }
}


class LibraryApp {
    public static void main(String[] args) {
        Library<Media> library = new Library<>();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nСистема управління бібліотекою");
            System.out.println("1. Додати книгу");
            System.out.println("2. Додати журнал");
            System.out.println("3. Додати DVD");
            System.out.println("4. Видалити ресурс");
            System.out.println("5. Пошук за назвою");
            System.out.println("6. Показати всі ресурси");
            System.out.println("7. Вийти");
            System.out.print("Оберіть опцію: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.print("Введіть назву книги: ");
                    String title = scanner.nextLine();
                    System.out.print("Введіть автора: ");
                    String author = scanner.nextLine();
                    library.addItem(new Book(title, author));
                    System.out.println("Книгу успішно додано.");
                }
                case 2 -> {
                    System.out.print("Введіть назву журналу: ");
                    String title = scanner.nextLine();
                    System.out.print("Введіть випуск: ");
                    String issue = scanner.nextLine();
                    library.addItem(new Magazine(title, issue));
                    System.out.println("Журнал успішно додано.");
                }
                case 3 -> {
                    System.out.print("Введіть назву DVD: ");
                    String title = scanner.nextLine();
                    System.out.print("Введіть тривалість (у хвилинах): ");
                    int duration = scanner.nextInt();
                    scanner.nextLine();
                    library.addItem(new DVD(title, duration));
                    System.out.println("DVD успішно додано.");
                }
                case 4 -> {
                    System.out.print("Введіть назву для видалення: ");
                    String title = scanner.nextLine();
                    library.removeItem(title);
                    System.out.println("Ресурс успішно видалено (якщо він існував)." );
                }
                case 5 -> {
                    System.out.print("Введіть назву для пошуку: ");
                    String title = scanner.nextLine();
                    List<Media> results = library.searchByTitle(title);
                    if (results.isEmpty()) {
                        System.out.println("Нічого не знайдено.");
                    } else {
                        results.forEach(System.out::println);
                    }
                }
                case 6 -> library.displayAll();
                case 7 -> {
                    System.out.println("Вихід...");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Невірна опція. Спробуйте ще раз.");
            }
        }
    }
}
