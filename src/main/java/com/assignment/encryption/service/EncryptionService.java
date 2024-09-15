package com.assignment.encryption.service;

import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.*;

@Service
public class EncryptionService {

    private KeyPair rsaKeyPair;

    public EncryptionService() throws NoSuchAlgorithmException {
        // Generate RSA key pair on service initialization
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
        keyPairGen.initialize(2048);
        this.rsaKeyPair = keyPairGen.generateKeyPair();
    }

    public String encryptMessage(String message, String encryptionType) throws Exception {
        if ("AES".equalsIgnoreCase(encryptionType)) {
            return encryptWithAES(message);
        } else if ("RSA".equalsIgnoreCase(encryptionType)) {
            return encryptWithRSA(message);
        }
        throw new IllegalArgumentException("Invalid encryption type");
    }

    private String encryptWithAES(String message) throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(128);
        SecretKey secretKey = keyGenerator.generateKey();
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedBytes = cipher.doFinal(message.getBytes());
        return new String(encryptedBytes); // For simplicity, convert bytes to string
    }

    private String encryptWithRSA(String message) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, rsaKeyPair.getPublic());
        byte[] encryptedBytes = cipher.doFinal(message.getBytes());
        return new String(encryptedBytes);
    }
}

