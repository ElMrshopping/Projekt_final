package org.example.AppResources;

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
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class VerificationTest {

@Mock
    Connection connection;
@Mock
    PreparedStatement preparedStatement;
@Mock
    ResultSet resultSet;

Verification verification;
@BeforeEach
void setUp(){
    verification = new Verification(connection);
}
@Test
    void verifyIbanexiste() throws SQLException {
    when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
    when(preparedStatement.executeQuery()).thenReturn(resultSet);
    when(resultSet.next()).thenReturn(true);

    boolean result = verification.verifyIban("DE32747874344318304637");
    assertTrue(result);

    }

@Test
    void verifyPin_existe() throws SQLException{
    when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
    when(preparedStatement.executeQuery()).thenReturn(resultSet);
    when(resultSet.next()).thenReturn(true);
    when(verification.verifyPin("aurel@gmail.com","456789")).thenReturn(true);
    boolean result = verification.verifyPin("aurel@gmail.com","456789");
    assertTrue(result);
    }
    @Test
    void verifyIbanincorrect()throws SQLException{
    when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
    when(preparedStatement.executeQuery()).thenReturn(resultSet);
    when(resultSet.next()).thenReturn(false);
    boolean response = verification.verifyIban("FFFGGG");
    assertFalse(response);
    }
    @Test
    void verifyPin_Incorrecte()throws SQLException{
    when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
    when(preparedStatement.executeQuery()).thenReturn(resultSet);
    when(resultSet.next()).thenReturn(false);
    boolean response = verification.verifyPin("bobytresor5@gmail.com" , "45789");
    assertFalse(response);
    }
}
