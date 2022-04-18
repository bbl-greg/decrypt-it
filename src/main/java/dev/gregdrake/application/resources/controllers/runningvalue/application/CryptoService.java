/* (C)2022 */
package dev.gregdrake.application.resources.controllers.runningvalue.application;

import com.google.inject.Inject;
import dev.gregdrake.application.resources.controllers.runningvalue.infastructure.CryptoUseCase;
import dev.gregdrake.application.resources.controllers.runningvalue.infastructure.VaultStore;
import dev.gregdrake.application.resources.controllers.runningvalue.model.RunningValues.RunningValues;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.HashMap;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CryptoService implements CryptoUseCase {
    private static final Logger logger = LoggerFactory.getLogger(CryptoService.class);

    private static final String AES_CIPHER_ALGORITHM = "AES/CBC/PKCS5PADDING";

    private final VaultStore vaultStore;

    @Inject
    CryptoService(VaultStore vaultStore) {
        this.vaultStore = vaultStore;
    }

    @Override
    public HashMap<String, String> encrypt(RunningValues input) {
        HashMap<String, String> encryptedRunningValues = new HashMap<>();
        Cipher cipher = getAndInitCipher(Cipher.ENCRYPT_MODE);

        try {
            // this can be made DRY
            byte[] encryptedAvg = cipher.doFinal(String.valueOf(input.getAverage()).getBytes());
            encryptedRunningValues.put("Average", Base64.getEncoder().encodeToString(encryptedAvg));

            byte[] encryptedStdDev = cipher.doFinal(String.valueOf(input.getStdDeviation()).getBytes());
            encryptedRunningValues.put("Std-Dev", Base64.getEncoder().encodeToString(encryptedStdDev));

        } catch (IllegalBlockSizeException | BadPaddingException e) {
            logger.error("Error Encrypting String: " + e.getMessage());
        }
        return encryptedRunningValues;
    }

    @Override
    public double decrypt(String input) {
        Double decryptedValue = null;
        Cipher cipher = getAndInitCipher(Cipher.DECRYPT_MODE);

        try {
        byte[] decodedString = Base64.getDecoder().decode(input);
        byte[] decryptValue = cipher.doFinal(decodedString);
        decryptedValue = Double.valueOf(new String(decryptValue));
        } catch (IllegalBlockSizeException | BadPaddingException e) {
            logger.error("Error Decrypting String: " + e.getMessage());
        }
        return decryptedValue;
    }

    private Cipher getAndInitCipher(int mode) {
        Cipher cipher = null;
        try {
            cipher = Cipher.getInstance(AES_CIPHER_ALGORITHM);
            cipher.init(mode, vaultStore.getKey(), getIvSpec());
        } catch (NoSuchPaddingException
                | NoSuchAlgorithmException
                | InvalidKeyException
                | InvalidAlgorithmParameterException e) {
            logger.error("Error getting Cipher instance: " + e.getMessage());
        }
        return cipher;
    }

    private IvParameterSpec getIvSpec() {
        return new IvParameterSpec(vaultStore.getIv());
    }
}
