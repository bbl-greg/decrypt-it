/* (C)2022 */
package dev.gregdrake.application.resources.controllers.runningvalue.application;

import com.google.inject.Inject;
import dev.gregdrake.application.resources.controllers.RunningValueController;
import dev.gregdrake.application.resources.controllers.runningvalue.infastructure.DataStore;
import dev.gregdrake.application.resources.controllers.runningvalue.infastructure.EncryptUseCase;
import dev.gregdrake.application.resources.controllers.runningvalue.infastructure.VaultStore;
import dev.gregdrake.application.resources.controllers.runningvalue.model.RunningValues.RunningValues;
import dev.gregdrake.infastructure.LocalVaultStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.HashMap;

  public class EncryptService implements EncryptUseCase {
    private static final Logger logger = LoggerFactory.getLogger(EncryptService.class);

    private static final String AES_CIPHER_ALGORITHM = "AES/CBC/PKCS5PADDING";

  private final VaultStore vaultStore;

  @Inject
  EncryptService(VaultStore vaultStore) {
    this.vaultStore = vaultStore;
  }
  @Override
  public HashMap<String, String> encrypt(RunningValues input) {
    HashMap<String, String> encryptedRunningValues = new HashMap<String, String>();
    Cipher cipher = getCipher();
    try {
    //initialize cipher with key and iv
    cipher.init(Cipher.ENCRYPT_MODE, vaultStore.getKey(), ivParameterSpec);

    byte[] encryptedAvg = cipher.doFinal(String.valueOf(input.getAverage()).getBytes());
    encryptedRunningValues.put("Average", Base64.getEncoder().encodeToString(encryptedAvg));
    } catch (InvalidAlgorithmParameterException | InvalidKeyException | IllegalBlockSizeException |
             BadPaddingException e) {
      logger.error("Error Encrypting String: " + e.getMessage());
    }
    return encryptedRunningValues;
  }

  private Cipher getCipher() {
    Cipher cipher = null;
    try {
    cipher = Cipher.getInstance(AES_CIPHER_ALGORITHM);
    cipher.init(Cipher.ENCRYPT_MODE, vaultStore.getKey(), getIvSpec());

    } catch (NoSuchPaddingException|NoSuchAlgorithmException|InvalidKeyException|InvalidAlgorithmParameterException e) {
      logger.error("Error getting Cipher instance: " + e.getMessage());
    }
    return cipher;
  }

  private IvParameterSpec getIvSpec() {
    return new IvParameterSpec(vaultStore.getIv());
  }

}
