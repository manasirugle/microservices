package com.example.user_service.service;


import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

@Service
public class EncryptionService {

    public String getEncryptedString(String password) {

        String key = "1234567890abcdef";
        String iv = "abcdef1234567890";
        String encryptedPassword = "";

        try {
            IvParameterSpec ivSpec = new IvParameterSpec(iv.getBytes("UTF-8"));
            SecretKeySpec keySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);

            byte[] encrypted = cipher.doFinal(password.getBytes("UTF-8"));
            return Base64.getEncoder().encodeToString(encrypted);

        } catch (Exception ex) {
            System.out.println("Exception occurred - " + ex.getMessage() + ex);
        }
        return encryptedPassword;
    }

}
