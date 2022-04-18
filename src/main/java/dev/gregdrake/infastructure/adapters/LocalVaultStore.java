/* (C)2022 */
package dev.gregdrake.infastructure.adapters;

import dev.gregdrake.infastructure.interfaces.VaultStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class LocalVaultStore implements VaultStore {
  public static final String AES = "AES";
  private static final Logger logger = LoggerFactory.getLogger(LocalVaultStore.class);
  private SecretKey secretKey = null;
  private byte[] initVector = null;

  @Override
  public SecretKey getKey() {
    if (this.secretKey == null) {
      this.secretKey = generateKey();
    }
    return this.secretKey;
  }

  @Override
  public byte[] getIv() {
    if (this.initVector == null) {
      this.initVector = generateIv();
    }
    return this.initVector;
  }

  private SecretKey generateKey() {
    SecretKey secretKey = null;
    try {
      SecureRandom secureRandom = new SecureRandom();
      KeyGenerator keyGenerator = KeyGenerator.getInstance(AES);
      keyGenerator.init(256, secureRandom);
      secretKey = keyGenerator.generateKey();
    } catch (NoSuchAlgorithmException e) {
      logger.error("Error generating decryption key: " + e.getMessage());
    }
    return secretKey;
  }

  private byte[] generateIv() {
    byte[] initVector = new byte[16];
    SecureRandom secureRandom = new SecureRandom();
    secureRandom.nextBytes(initVector);
    return initVector;
  }
}
