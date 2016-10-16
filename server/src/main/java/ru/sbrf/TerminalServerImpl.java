package ru.sbrf;

import java.util.Random;

/**
 * Created by Ivan on 24/09/16.
 */
public class TerminalServerImpl implements TerminalServer {

    private int balance;

    public TerminalServerImpl(int balance) {
        this.balance = balance;
    }

    public void get(int amount) throws NotEnoughMoneyException, BadMoneyAmountException,
            MaintenanceWorkInProgressException, TerminalServerBusyException {
        makeRandomException();

        if (amount % 100 != 0)
            throw new BadMoneyAmountException(amount);

        if (balance < amount)
            throw new NotEnoughMoneyException();

        balance -= amount;
    }

    public void put(int amount) throws BadMoneyAmountException, MaintenanceWorkInProgressException,
            TerminalServerBusyException {
        makeRandomException();

        if (amount % 100 != 0)
            throw new BadMoneyAmountException(amount);

        balance += amount;
    }

    public int balance() throws MaintenanceWorkInProgressException, TerminalServerBusyException {
        makeRandomException();

        return balance;
    }

    // исскусственные exception
    private void makeRandomException() throws TerminalServerBusyException,
            MaintenanceWorkInProgressException {
        int rand = new Random().nextInt(1000);

        switch (rand) {
            case 34:
                throw new TerminalServerBusyException();
            case 499:
                throw new MaintenanceWorkInProgressException();
        }

    }
}
