package ru.sbrf;

import org.junit.Assert;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


/**
 * Created by Ivan on 16/10/16.
 */
public class TerminalImplTest {
    @Test
    public void balance() throws Exception {
        TerminalServer server = mock(TerminalServer.class);
        when(server.balance()).thenReturn(100);
        TerminalImpl terminal = new TerminalImpl("1111", server);
        terminal.login("1111");
        Assert.assertTrue(terminal.balance() == 100);
    }

    @Test(expected = NotAuthenticatedException.class)
    public void putNotAuth() throws Exception {
        TerminalServer server = mock(TerminalServer.class);
        TerminalImpl terminal = new TerminalImpl("1111", server);
        terminal.put(34);
    }
}