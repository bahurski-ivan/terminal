package ru.sbrf;

/**
 * Created by Ivan on 24/09/16.
 */
public class TerminalUserSession implements TerminalAction, Runnable {

    private Terminal terminal;
    private TerminalManipulator manipulator;

    public TerminalUserSession(Terminal terminal, TerminalManipulator manipulator) {
        this.terminal = terminal;
        this.manipulator = manipulator;

        manipulator.setActionReceiver(this);
    }

    public void performGet(int amount) {

        try {
            terminal.get(amount);
        } catch (TerminalException e) {
            manipulator.onError("Не удалось осуществить списание денег: " + e.getMessage());
        }

    }

    public void performPut(int amount) {

        try {
            terminal.put(amount);
        } catch (TerminalException e) {
            manipulator.onError("Ошибка пополнения баланса: " + e.getMessage());
        }

    }

    public void performLogin(String pin) {

        try {
            if (terminal.login(pin))
                manipulator.onMessage("Pin код верный, взаимодействие с терминалом разрешено");
            else
                manipulator.onMessage("Неправильный pin код");
        } catch (TerminalException e) {
            manipulator.onError("Ошибка: " + e.getMessage());
        }

    }

    public void performBalance() {

        try {
            int balance = terminal.balance();

            manipulator.onMessage("Баланс -- " + balance);
        } catch (TerminalException e) {
            manipulator.onError("Ошибка получения баланса: " + e.getMessage());
        }

    }

    public void run() {
        while (manipulator.waitForCommand()) ;
    }
}
