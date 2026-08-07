package org.example.Clients;

import org.example.AppResources.Verification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BalanceTest {
    @Mock
    Verification verification;
    @Mock
    Connection connection;
    @Mock
    PreparedStatement preparedStatement;
    @Mock
    ResultSet resultSet;

     Balance balance;
    @BeforeEach
    void setUp() {
        balance = new Balance(connection  , verification);
    }

    @Test
    void checksolde() throws SQLException {
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true);

        when(verification.verifyIban("DE32747874344318304637")).thenReturn(true);
        boolean result = balance.checksolde("DE32747874344318304637");
        assertTrue(result);
        verify(connection , times(1)).prepareStatement(anyString());
    }
}