package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class SessionTests {

    private PrintStream printStream;
    private BufferedReader reader;
    private Authenticator authenticator;
    private Session session;

    @Before
    public void setUp() throws IOException {
        printStream = mock(PrintStream.class);
        reader = mock(BufferedReader.class);
        authenticator = mock(Authenticator.class);
        session = new Session(printStream, reader, authenticator);

        when(reader.readLine()).thenReturn("test_account_num").thenReturn("test_password");
        when(authenticator.accountNumIsValid(anyString())).thenReturn(true);
        when(authenticator.passwordIsValid(anyString(), anyString())).thenReturn(true);
    }

    @Test
    public void shouldAuthenticateAccountNumberAndPassword() throws IOException {
        session.login();
        verify(authenticator).accountNumIsValid(anyString());
        verify(authenticator).passwordIsValid(anyString(), anyString());
    }

    @Test
    public void shouldAskUserForDetailsWhenListingUserDetails() throws IOException {
        User user = mock(User.class);
        when(authenticator.getUser(anyString())).thenReturn(user);
        when(user.getDetailString()).thenReturn("");

        session.login();
        session.printUserDetails();
        verify(user).getDetailString();
    }
}
