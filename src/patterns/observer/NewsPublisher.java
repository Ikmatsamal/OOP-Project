package patterns.observer;

import java.util.ArrayList;
import java.util.List;

public class NewsPublisher {

    private List<Observer> observers;

    public NewsPublisher() {
        observers = new ArrayList<>();
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    public void notifyObservers(String news) {

        for (Observer observer : observers) {
            observer.update(news);
        }

    }

}