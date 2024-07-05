package com;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;
import java.io.*;
import java.nio.file.Files;
import javax.crypto.KeyGenerator;

public class Cypher {

    private static final String KEY_FILE = "secret.key"; // Key file path
    private static final String SALT = "pasindu@Slgym"; // Add your salt for added security

    private static SecretKey secretKey;

    private static void generateOrLoadSecretKey() throws NoSuchAlgorithmException, IOException, InvalidKeySpecException {
        File keyFile = new File(KEY_FILE);

        if (keyFile.exists()) {
            // Load the key from the key file if it exists
            byte[] keyBytes = Files.readAllBytes(keyFile.toPath());
            KeySpec keySpec = new PBEKeySpec(SALT.toCharArray(), keyBytes, 65536, 128);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            byte[] encoded = factory.generateSecret(keySpec).getEncoded();
            secretKey = new SecretKeySpec(encoded, "AES");
        } else {
            // Generate a new key if the key file doesn't exist
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(128); // 128-bit key
            secretKey = keyGenerator.generateKey();
            // Save the key to the key file
            byte[] encoded = secretKey.getEncoded();
            try (FileOutputStream fos = new FileOutputStream(keyFile)) {
                fos.write(encoded);
            }
        }
    }

    public static String encrypt(String plainText) throws Exception {
        generateOrLoadSecretKey();
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedBytes = cipher.doFinal(plainText.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    public static String decrypt(String encryptedText) throws Exception {
        generateOrLoadSecretKey();
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] encryptedBytes = Base64.getDecoder().decode(encryptedText);
        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
        return new String(decryptedBytes);
    }
    
}
