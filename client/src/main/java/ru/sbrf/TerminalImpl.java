package ru.sbrf;

/**
 * Created by Ivan on 24/09/16.
 */
public class TerminalImpl implements Terminal {

    private TerminalServer server;
    private PinValidator validator;

    public TerminalImpl(String rightPin, TerminalServer server) {
        this.server = server;
        validator = new PinValidator(rightPin);
    }

    public int balance() throws AccountIsLockedException, MaintenanceWorkInProgressException,
            NotAuthenticatedException, TerminalServerBusyException {
        validator.ensureAuth();
        return server.balance();
    }

    public void put(int amount) throws AccountIsLockedException, MaintenanceWorkInProgressException,
            NotAuthenticatedException, TerminalServerBusyException, BadMoneyAmountException {
        validator.ensureAuth();
        server.put(amount);
    }

    public void get(int amount) throws AccountIsLockedException, MaintenanceWorkInProgressException,
            NotAuthenticatedException, TerminalServerBusyException, BadMoneyAmountException, NotEnoughMoneyException {
        validator.ensureAuth();
        server.get(amount);
    }

    public boolean login(String pin) throws AccountIsLockedException {
        validator.throwOnLock();
        return validator.isValid(pin);
    }
}
