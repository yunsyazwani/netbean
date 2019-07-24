/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cc.ccsystem;

import java.lang.reflect.Array;
import java.util.Scanner;

/**
 *
 * @author syazwani.s
 */
public class CCSystem {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner scanInput = new Scanner(System.in);
        System.out.print("Input: ");
        String input = scanInput.nextLine();
        
        Scanner scanShift = new Scanner(System.in);
        System.out.print("Shifted: ");
        int shift = scanShift.nextInt();
            
        for(int i = 0; i<input.length(); i++){
            char ch = (char)(((int)input.charAt(i) + 
                                  shift - 97) % 26 + 97); 
            System.out.print("Result : " + ch);
            
        }
        System.out.println();
        
        char hc = (char)(((int)ch-shift-97)%26+97);
        //decrytped
        System.out.print("Back Shifted Result: " + hc);
        
    }

 
    
}
