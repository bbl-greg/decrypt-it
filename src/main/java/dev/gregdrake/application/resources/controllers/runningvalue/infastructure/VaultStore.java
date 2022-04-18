/* (C)2022 */
package dev.gregdrake.application.resources.controllers.runningvalue.infastructure;

import javax.crypto.SecretKey;

public interface VaultStore {
    SecretKey getKey();

    byte[] getIv();
}
