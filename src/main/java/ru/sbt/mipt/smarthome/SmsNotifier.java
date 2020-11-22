package ru.sbt.mipt.smarthome;

public class SmsNotifier implements Notifier {
    @Override
    public void doNotify(String message) {
        System.out.println(message);
    }
}
