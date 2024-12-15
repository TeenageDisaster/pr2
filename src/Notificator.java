import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


class Logger {
    private static Logger instance;
    private FileWriter fileWriter;

    private Logger() {
        try {
            fileWriter = new FileWriter("log.txt", true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }

    public void logToFile(String message) {
        try {
            fileWriter.write(message + "\n");
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void logToConsole(String message) {
        System.out.println(message);
    }
}


interface Notification {
    void send(String message);
}


class EmailNotification implements Notification {
    @Override
    public void send(String message) {
        Logger.getInstance().logToConsole("Email сповіщення: " + message);
    }
}

class SMSNotification implements Notification {
    @Override
    public void send(String message) {
        Logger.getInstance().logToConsole("SMS сповіщення: " + message);
    }
}

class PushNotification implements Notification {
    @Override
    public void send(String message) {
        Logger.getInstance().logToConsole("Push сповіщення: " + message);
    }
}


class NotificationFactory {
    public static Notification createNotification(String type) {
        return switch (type.toLowerCase()) {
            case "email" -> new EmailNotification();
            case "sms" -> new SMSNotification();
            case "push" -> new PushNotification();
            default -> throw new IllegalArgumentException("Невідомий тип сповіщення: " + type);
        };
    }
}


interface Subscriber {
    void update(String news);
}

class NewsAgency {
    private List<Subscriber> subscribers;

    public NewsAgency() {
        subscribers = new ArrayList<>();
    }

    public void addSubscriber(Subscriber subscriber) {
        subscribers.add(subscriber);
    }

    public void removeSubscriber(Subscriber subscriber) {
        subscribers.remove(subscriber);
    }

    public void notifySubscribers(String news) {
        for (Subscriber subscriber : subscribers) {
            subscriber.update(news);
        }
    }

    public void generateNews(String news) {
        Logger.getInstance().logToConsole("Новина згенерована: " + news);
        notifySubscribers(news);
    }
}

class NewsSubscriber implements Subscriber {
    private String name;

    public NewsSubscriber(String name) {
        this.name = name;
    }

    @Override
    public void update(String news) {
        Logger.getInstance().logToConsole(name + " отримав новину: " + news);
    }
}


 class Application {
    public static void main(String[] args) {
        Logger logger = Logger.getInstance();


        Notification email = NotificationFactory.createNotification("email");
        Notification sms = NotificationFactory.createNotification("sms");
        Notification push = NotificationFactory.createNotification("push");

        email.send("Ласкаво просимо до системи!");
        sms.send("Ваш OTP код: 1234.");
        push.send("У вас нове повідомлення.");


        NewsAgency agency = new NewsAgency();
        NewsSubscriber subscriber1 = new NewsSubscriber("Підписник 1");
        NewsSubscriber subscriber2 = new NewsSubscriber("Підписник 2");

        agency.addSubscriber(subscriber1);
        agency.addSubscriber(subscriber2);

        agency.generateNews("Термінова новина: Патерн Observer успішно реалізовано!");

        agency.removeSubscriber(subscriber1);
        agency.generateNews("Інша новина: Підписник 1 відписався.");
    }
}
