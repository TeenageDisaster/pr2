import java.util.*;

class University {
    private String name;
    private List<Department> departments;

    public University(String name) {
        this.name = name;
        this.departments = new ArrayList<>();
    }

    public class Department {
        private String name;
        private List<String> professors;

        public Department(String name) {
            this.name = name;
            this.professors = new ArrayList<>();
        }

        public void addProfessor(String professor) {
            professors.add(professor);
        }

        public List<String> getProfessors() {
            return professors;
        }

        @Override
        public String toString() {
            return "Кафедра: " + name + ", Викладачі: " + professors;
        }
    }


    public static class Helper {
        public static double calculateAverageGrade(List<Integer> grades) {
            if (grades.isEmpty()) {
                return 0.0;
            }
            int sum = 0;
            for (int grade : grades) {
                sum += grade;
            }
            return sum / (double) grades.size();
        }
    }


    public void manageEvents() {
        Event conference = new Event() {
            @Override
            public void organize(String eventName) {
                System.out.println("Організація події: " + eventName);
            }
        };
        conference.organize("Наукова конференція");
    }

    public void addDepartment(String departmentName) {
        departments.add(new Department(departmentName));
    }

    public List<Department> getDepartments() {
        return departments;
    }


    interface Event {
        void organize(String eventName);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        University university = new University("Технічний Університет");

        while (true) {
            System.out.println("\n--- Меню університету ---");
            System.out.println("1. Додати кафедру");
            System.out.println("2. Додати викладача до кафедри");
            System.out.println("3. Переглянути кафедри");
            System.out.println("4. Розрахувати середній бал студентів");
            System.out.println("5. Організувати подію");
            System.out.println("6. Вийти");
            System.out.print("Оберіть опцію: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.print("Введіть назву кафедри: ");
                    String departmentName = scanner.nextLine();
                    university.addDepartment(departmentName);
                }
                case 2 -> {
                    if (university.getDepartments().isEmpty()) {
                        System.out.println("Немає доступних кафедр.");
                        break;
                    }
                    System.out.println("Доступні кафедри:");
                    for (int i = 0; i < university.getDepartments().size(); i++) {
                        System.out.println((i + 1) + ". " + university.getDepartments().get(i).name);
                    }
                    System.out.print("Оберіть номер кафедри: ");
                    int deptIndex = scanner.nextInt() - 1;
                    scanner.nextLine();
                    if (deptIndex >= 0 && deptIndex < university.getDepartments().size()) {
                        System.out.print("Введіть ім'я викладача: ");
                        String professor = scanner.nextLine();
                        university.getDepartments().get(deptIndex).addProfessor(professor);
                    } else {
                        System.out.println("Неправильний номер кафедри.");
                    }
                }
                case 3 -> {
                    if (university.getDepartments().isEmpty()) {
                        System.out.println("Немає доданих кафедр.");
                    } else {
                        for (Department dept : university.getDepartments()) {
                            System.out.println(dept);
                        }
                    }
                }
                case 4 -> {
                    System.out.print("Введіть кількість оцінок: ");
                    int n = scanner.nextInt();
                    List<Integer> grades = new ArrayList<>();
                    for (int i = 0; i < n; i++) {
                        System.out.print("Введіть оцінку " + (i + 1) + ": ");
                        grades.add(scanner.nextInt());
                    }
                    double average = Helper.calculateAverageGrade(grades);
                    System.out.println("Середній бал студентів: " + average);
                }
                case 5 -> university.manageEvents();
                case 6 -> {
                    System.out.println("Вихід...");
                    return;
                }
                default -> System.out.println("Неправильна опція. Спробуйте ще раз.");
            }
        }
    }
}
