/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trainingcrypto;

import javax.crypto.SecretKey;
import javax.xml.bind.DatatypeConverter;
//import static org.junit.Assert.*;

/**
 *
 * @author syazwani.s
 */
class CryptoTest{
    
   @Test
   public void createAESKey()throws Exception{
        SecretKey key = Crypto.createAESKey();
//        assertNotNull(key);
        System.out.println(DatatypeConverter.printHexBinary(key.getEncoded()));
   }
   
   @Test
   public void testAESCryptoRoutine() throws Exception{
       SecretKey key = Crypto.createAESKey();
       byte[] initializationVector = Crypto.createInitialzationVector();
       String plainText = "This is the text we are going to hide in plain sight";
       byte[] cipherText = Crypto.performAESDecryption(plainText, key, initializationVector);
//       assertNotNull(cipherText);
       System.out.println(DatatypeConverter.printHexBinary(cipherText));
       String decryptedText = Crypto.performAESDecryption(cipherText, key, initializationVector);
//       assertEquals(plainText, decryptedText);
       System.out.println(decryptedText);
   }
}
