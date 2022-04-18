/* (C)2022 */
package dev.gregdrake.infastructure.interfaces;

import javax.crypto.SecretKey;

public interface VaultStore {
  SecretKey getKey();

  byte[] getIv();
}
