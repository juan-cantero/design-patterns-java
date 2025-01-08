package io.juanqui.prueba;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.spec.KeySpec;
import java.util.Base64;

public class CryptoUtil {

    private static final String ALGORITHM = "AES/CBC/PKCS5Padding";
    private static final String SECRET_KEY_FACTORY_ALGORITHM = "PBKDF2WithHmacSHA1";

    public static void main(String[] args) {
        try {
            String data = "Hello, World!";  // Data to encrypt
            String key = "your128bitkey123";  // Key (must be 128-bit, 16 characters)
            String salt = "someSalt";  // Salt (same salt used in both encryption and decryption)

            // Encrypt the data
            String encryptedData = encrypt(data, key, salt);
            System.out.println("Encrypted Data: " + encryptedData);

            // Decrypt the data
            String decryptedData = decrypt(encryptedData, key, salt);
            System.out.println("Decrypted Data: " + decryptedData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String encrypt(String data, String key, String salt) throws Exception {
        SecretKeySpec secretKeySpec = generateKey(key, salt);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        IvParameterSpec ivSpec = new IvParameterSpec(new byte[16]); // 16-byte IV (all zeroes)
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivSpec);

        byte[] encryptedBytes = cipher.doFinal(data.getBytes("UTF-8"));
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    public static String decrypt(String encryptedData, String key, String salt) throws Exception {
        SecretKeySpec secretKeySpec = generateKey(key, salt);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        IvParameterSpec ivSpec = new IvParameterSpec(new byte[16]); // 16-byte IV (all zeroes)
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivSpec);

        byte[] decodedBytes = Base64.getDecoder().decode(encryptedData);
        byte[] decryptedBytes = cipher.doFinal(decodedBytes);
        return new String(decryptedBytes, "UTF-8");
    }

    private static SecretKeySpec generateKey(String key, String salt) throws Exception {
        SecretKeyFactory factory = SecretKeyFactory.getInstance(SECRET_KEY_FACTORY_ALGORITHM); // "PBKDF2WithHmacSHA1"
        KeySpec spec = new PBEKeySpec(key.toCharArray(), salt.getBytes(), 1000, 128);  // 128 bits = 16 bytes
        byte[] secretKey = factory.generateSecret(spec).getEncoded();
        return new SecretKeySpec(secretKey, 0, 16, "AES");  // 16 bytes = 128-bit key
    }
}

