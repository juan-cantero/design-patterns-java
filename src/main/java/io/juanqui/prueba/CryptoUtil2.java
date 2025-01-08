package io.juanqui.prueba;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.util.Base64;

public class CryptoUtil2 {

    private static final String ALGORITHM = "AES/CBC/PKCS5Padding";
    private static final int IV_SIZE = 16; // 16 bytes for AES
    private static final int KEY_SIZE = 128; // 128-bit key

    public static void main(String[] args) {
        try {
            String data = "Hello, World !"; // Data to encrypt
            String key = "your128bitkey123"; // Key (16 characters for 128-bit key)

            // Encrypt data
            String encryptedData = encryptWithRandomIV(data, key);
            System.out.println("Encrypted Data (IV prepended): " + encryptedData);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Encrypts the given plaintext using AES/CBC/PKCS5Padding with a random IV.
     * Prepends the IV to the encrypted Base64-encoded ciphertext.
     *
     * @param plainText the text to encrypt
     * @param keyText the encryption key (must be 16 bytes)
     * @return a Base64 string containing the IV and ciphertext
     * @throws Exception if encryption fails
     */
    public static String encryptWithRandomIV(String plainText, String keyText) throws Exception {
        // Convert key to bytes
        byte[] keyBytes = keyText.getBytes("UTF-8");

        // Generate a random IV
        byte[] ivBytes = generateRandomIV();
        IvParameterSpec ivParameterSpec = new IvParameterSpec(ivBytes);

        // Initialize the Cipher
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        SecretKeySpec secretKey = new SecretKeySpec(keyBytes, "AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivParameterSpec);

        // Perform encryption
        byte[] cipherText = cipher.doFinal(plainText.getBytes("UTF-8"));

        // Encode IV and Ciphertext as Base64
        String ivBase64 = Base64.getEncoder().encodeToString(ivBytes);
        String cipherTextBase64 = Base64.getEncoder().encodeToString(cipherText);

        // Prepend IV to ciphertext
        return ivBase64 + ":" + cipherTextBase64;
    }

    /**
     * Generates a random IV for AES encryption.
     *
     * @return a random 16-byte array
     */
    private static byte[] generateRandomIV() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] iv = new byte[IV_SIZE];
        secureRandom.nextBytes(iv);
        return iv;
    }

}