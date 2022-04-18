package dev.gregdrake.application.businesslogic;

import dev.gregdrake.domain.runningvalues.entities.RunningValuesEntity;
import dev.gregdrake.domain.runningvalues.interfaces.RunningValues;
import dev.gregdrake.infastructure.adapters.LocalVaultStore;
import dev.gregdrake.infastructure.interfaces.VaultStore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CryptoServiceTest {
  CryptoService sut;

  VaultStore localVaultStore;

  @BeforeEach
  void setUp() {
    localVaultStore = new LocalVaultStore();
    sut = new CryptoService(localVaultStore);
  }

  @Test
  public void testRunThroughEncryptAndDecrypt() {
    double averageToTest = 4.345;
    double stdDevToTest = 1.3345;
    RunningValues valuesToEncrypt = new RunningValuesEntity(averageToTest, stdDevToTest);

    HashMap<String, String> encryptedValues = sut.encrypt(valuesToEncrypt);
    // Don't use a string literal like this in real prod code, this is simple for coding speed.
    double decryptedStdDevToTest = sut.decrypt(encryptedValues.get("Std-Dev"));
    double decryptedAverageToTest = sut.decrypt(encryptedValues.get("Average"));

    assertEquals(stdDevToTest, decryptedStdDevToTest);
    assertEquals(averageToTest, decryptedAverageToTest);
  }
}
