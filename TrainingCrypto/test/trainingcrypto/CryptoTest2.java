/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trainingcrypto;

import javax.crypto.SecretKey;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author syazwani.s
 */
public class CryptoTest2 {
    
    public CryptoTest2() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of createAESKey method, of class Crypto.
     */
    @org.junit.Test
    public void testCreateAESKey() throws Exception {
        System.out.println("createAESKey");
        SecretKey expResult = null;
        SecretKey result = Crypto.createAESKey();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createInitialzationVector method, of class Crypto.
     */
    @org.junit.Test
    public void testCreateInitialzationVector() {
        System.out.println("createInitialzationVector");
        byte[] expResult = null;
        byte[] result = Crypto.createInitialzationVector();
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of performAESEncryption method, of class Crypto.
     */
    @org.junit.Test
    public void testPerformAESEncryption() throws Exception {
        System.out.println("performAESEncryption");
        String plainText = "";
        SecretKey secretKey = null;
        byte[] initializationVector = null;
        byte[] expResult = null;
        byte[] result = Crypto.performAESEncryption(plainText, secretKey, initializationVector);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of performAESDecryption method, of class Crypto.
     */
    @org.junit.Test
    public void testPerformAESDecryption() throws Exception {
        System.out.println("performAESDecryption");
        byte[] cipherText = null;
        SecretKey secretKey = null;
        byte[] initializationVector = null;
        String expResult = "";
        String result = Crypto.performAESDecryption(cipherText, secretKey, initializationVector);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
