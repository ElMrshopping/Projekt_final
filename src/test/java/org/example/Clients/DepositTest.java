package org.example.Clients;

import org.example.AppResources.Generate_Recu;
import org.example.AppResources.Verification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
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
class DepositTest {
@Mock
    Connection connection;
@Mock
    Verification verification;
@Mock
    Generate_Recu generate_recu;
@Mock
    PreparedStatement preparedStatement;
@Mock
    ResultSet resultSet;
Deposit deposit;
@BeforeEach
void setUp(){
    deposit = new Deposit(connection , verification , generate_recu );
}
    @Test
    void deposit_ibanValid() throws SQLException {
    when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
    when(preparedStatement.executeQuery()).thenReturn(resultSet);
    when(resultSet.next()).thenReturn(true);
    when(verification.verifyIban("DE32747874344318304637")).thenReturn(true);
    when(generate_recu.generer_recu(5000 , "DE32747874344318304637")).thenReturn(true);
    boolean result = deposit.deposit("DE32747874344318304637" , 5000);
    assertTrue(result);
    }
    @ParameterizedTest
    @CsvSource(
            {"ABCDEF , 5000" ,
              "ERFG ,  8000" ,
               "RRTTTTT ,  80044"
            }
    )
    void deposit_ibanInvalid(String iban , int montant) throws SQLException{
    when(verification.verifyIban(iban)).thenReturn(false);
    boolean resultat = deposit.deposit(iban  , montant);
    assertFalse(resultat);
    verify(connection , never()).prepareStatement(anyString());
    }

}

