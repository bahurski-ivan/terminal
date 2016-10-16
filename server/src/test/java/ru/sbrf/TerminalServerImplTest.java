package ru.sbrf;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Ivan on 16/10/16.
 */
public class TerminalServerImplTest {
    private static int INITIAL_MONEY_VALUE = 1000;

    @Test
    public void getSimple() throws Exception {
        boolean tryAgain = false;
        TerminalServerImpl server = new TerminalServerImpl(INITIAL_MONEY_VALUE);

        do {
            try {
                server.get(100);
                Assert.assertTrue(server.balance() == 900);
            } catch (TerminalServerBusyException | MaintenanceWorkInProgressException e) {
                tryAgain = true;
            }
        } while (tryAgain);
    }

    @Test(expected = NotEnoughMoneyException.class)
    public void getTooMany() throws Exception {
        TerminalServerImpl server = new TerminalServerImpl(INITIAL_MONEY_VALUE);

        boolean tryAgain = false;
        do {
            try {
                server.get(100_000_000);
            } catch (TerminalServerBusyException | MaintenanceWorkInProgressException e) {
                tryAgain = true;
            }
        } while (tryAgain);
    }

    @Test
    public void put() throws Exception {
        TerminalServerImpl server = new TerminalServerImpl(INITIAL_MONEY_VALUE);

        boolean tryAgain = false;
        do {
            try {

                int curr = server.balance();
                server.put(100);
                Assert.assertTrue(server.balance() == curr + 100);

            } catch (TerminalServerBusyException | MaintenanceWorkInProgressException e) {
                tryAgain = true;
            }
        } while (tryAgain);
    }
}