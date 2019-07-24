/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trainingcrypto;
import java.security.SecureRandom;
import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;

/**
 *
 * @author syazwani.s
 */
public class Crypto {
    
    private static final String AES = "AES";
    private static final String AES_CIPHER_ALGORITHM = "AES/CBC/PKCS5Padding";
    
    public static SecretKey createAESKey()throws Exception{
        SecureRandom secureRandom = new SecureRandom();
        KeyGenerator keyGenerator = KeyGenerator.getInstance(AES);
        keyGenerator.init(256, secureRandom);
        return keyGenerator.generateKey();
    }
    
    public static byte[] createInitialzationVector(){
        byte[] initializationVector = new byte[16];
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(initializationVector);
        return initializationVector;
    }
    
    public static byte[] performAESEncryption(String plainText, SecretKey secretKey, byte[] initializationVector) throws Exception{
        Cipher cipher = Cipher.getInstance(AES_CIPHER_ALGORITHM);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(initializationVector);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivParameterSpec);
        return cipher.doFinal(plainText.getBytes());
    }
    
    public static String performAESDecryption(byte[] cipherText, SecretKey secretKey, byte[] initializationVector)throws Exception{
        Cipher cipher = Cipher.getInstance(AES_CIPHER_ALGORITHM);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(initializationVector);
        cipher.init(Cipher.DECRYPT_MODE, secretKey, ivParameterSpec);
        byte[] result = cipher.doFinal(cipherText);
        return new String(result);
    }
}
