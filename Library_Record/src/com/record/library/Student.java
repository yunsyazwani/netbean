/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.record.library;

import java.io.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author syazwani.s
 */
public class Student {
    
    public static void Menu() {
      
      int menu;
      System.out.println();
      System.out.println("------Menu------");
      System.out.println();
      System.out.println("1 - View Book");
      System.out.println("2 - Borrow Book");
      System.out.println("3 - Return Book");
      System.out.println("4 - View Borrow Book");
      System.out.println("5 - Search Book");
      System.out.println("6 - Quit");
      System.out.print("Choose option : ");
      Scanner input = new Scanner(System.in);
      menu = input.nextInt();
      
      switch(menu){
          case 1:
              Student.viewBook();
              break;
          case 2:
              borrowBook();
              break;
          case 3:
              returnBook();
              break;
          case 4:
              viewBorrow();
              break;
          case 5:
              searchBook();
              break;
          case 6:
              System.out.println();
              System.out.println("Goodbye...");
              System.exit(0);              
              break;
          default:
              Menu();
      }
    }
       
    
    public static void viewBook(){
        int option;
        String categ ;
        Scanner choose = new Scanner(System.in);
            
        System.out.println();
        System.out.println("1 - Novel ");
        System.out.println("2 - Comics");
        System.out.print("Choose Category : ");
        option = choose.nextInt();
        System.out.println();
            if(option == 1){
                categ = "Novel";
            }
            else{
                categ = "Comics";
            }
        
        //read all that have chosen category
        String filepath = "book.txt";
        
        try {
            Scanner filebook = new Scanner(new File(filepath));
            
            while(filebook.hasNextLine()){
                
                String line = filebook.nextLine();
                
                if(line.contains(categ)){
                    String[] split = line.split(",");
                    System.out.println("Title : " + split[0]);
                    System.out.println("Author : " + split[1]);
                    System.out.println("Book ID : " + split[2]);
                    System.out.println("Category : " + split[3]);
                    System.out.println("Location : " + split[4]);
                    System.out.println();
                }
            }
           
           filebook.close();
           Menu();
           
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void borrowBook(){
         try{
            boolean found = false;
            String filename= "borrow.txt";
            FileWriter fw = new FileWriter(filename,true); //the true will append the new data
            
            String name, id, faculty, bookID;
            
            System.out.println();
            System.out.println("------Borrow Book-------");
            System.out.println();

            Scanner input = new Scanner(System.in);
            System.out.print("Enter Name : ");
            name = input.nextLine();
            System.out.print("Enter ID No : ");
            id = input.nextLine();
            System.out.print("Enter Faculty : ");
            faculty = input.nextLine();
            System.out.print("Enter Book ID : ");
            bookID = input.nextLine();
        
            String scanName, scanId, scanFaculty, scanBookID = "";
                        
            //compare if have same book id in the file, retry borrow book
            
            try{
                Scanner scan = new Scanner(new File("borrow.txt"));
                scan.useDelimiter("[,\n]");
    
                while(scan.hasNext() && !found){
                    scanName = scan.next();
                    scanId = scan.next();
                    scanFaculty = scan.next();
                    scanBookID = scan.next();
   
                    if(scanBookID.trim().equals(bookID.trim()) ){
                        found = true;                    
                    }
                }
                scan.close();
            }
            catch(Exception ex){
                ex.printStackTrace();
            }
            
            if(found == true){
                //already exist
                System.out.println("Book already Borrowed ! Make sure the book have the correct ID as inserted.");
                borrowBook();
            } else {

                // else, add to txt file          
                LocalDate dateReturn = LocalDate.now().plusDays(7);
                fw.write(name + ", " + id + ", " + faculty + ", " + bookID + ", " + dateReturn);
                fw.write(System.getProperty( "line.separator" ));                
                fw.close();
                
                // not found , and created, only then go to menu .  
                System.out.println("Must return on "+ dateReturn);
                System.out.println("If you late return the book by 1 day = RM 1.00");
                Menu();
            }
            
        }
        catch(IOException ioe){
            System.err.println("IOException: " + ioe.getMessage());
        }
    }

    public static void returnBook(){
        String oriFilepath = "borrow.txt";
        
        Scanner book = new Scanner(System.in);
        System.out.println();
        System.out.println("-----Return Book------");
        System.out.println();
        System.out.print("Book ID to return : ");
        String bookReturn = book.next();
        
        String tempFile = "temp.txt";
        File oldFile = new File(oriFilepath);
        File newFile = new File(tempFile);
        String name = "";
        String id = "";
        String faculty = "";
        String bookID = "";
        String dateReturn = "";
              
         //read date return
        String filepath = "borrow.txt";
        
        try {
            Scanner fileborrow = new Scanner(new File(filepath));
            
            while(fileborrow.hasNextLine()){
                
                String line = fileborrow.nextLine();
                
                if(line.contains(bookReturn)){
                    String[] split = line.split(",");
                    System.out.println();
                    System.out.println("Name : " + split[0]);
                    System.out.println("ID : " + split[1]);
                    System.out.println("Faculty : " + split[2]);
                    System.out.println("Book ID : " + split[3]);
                    System.out.println("Date Return : " + split[4]);
                    System.out.println();
                    
                     //baca tarikh kalau lambat return, kena bayar berapa, lebih sehari rm1
                    LocalDate dateMustReturn = LocalDate.parse(split[4].trim());    
                    LocalDate dateNow = LocalDate.now();
                    long days = ChronoUnit.DAYS.between(dateNow, dateMustReturn);
                    System.out.println("Days between: " + days);
                    double fees = days * 1;
                    System.out.println("Fees (1 day = RM1): RM " + String.format("%.2f", fees));
                    System.out.println();
                    System.out.println("Please pay the fee first. Thank you.");
                    System.out.println();
                }
            }
           
           fileborrow.close();
           
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
        }   
         
        //soalan kluar dah bayar ke belum, kalau dah, proceed delete kat borrow.txt, kalau tak, menu.
        Scanner pay = new Scanner(System.in);
        System.out.print("Payment have made ? (yes/no) : ");
        String payment = pay.next();
        
        if("yes".equals(payment)){
                
            //delete in borrow.txt
            try{
                FileWriter fw = new FileWriter(tempFile,true);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter pw = new PrintWriter(bw);
                Scanner scan = new Scanner(new File(oriFilepath));
                scan.useDelimiter("[,\n]");

                while(scan.hasNext()){
                    name= scan.next();
                    id = scan.next();
                    faculty = scan.next();
                    bookID = scan.next();
                    dateReturn = scan.next();

                    if(!bookID.trim().equals(bookReturn.trim()) ){ //if text found not equal with bookReturn, write in temp.txt
                        pw.println(name + ", " + id + ", " + faculty + ", " + bookID + ", " + dateReturn);
                    }
                }
                scan.close();
                pw.flush();
                pw.close();
                oldFile.delete();
                File dump = new File(oriFilepath);
                newFile.renameTo(dump);
                System.out.println("Return completed!");
                Menu();
            }
            catch(Exception ex){
                ex.printStackTrace();
            }
        }
        else{
            Menu();
        }
    }
    
    public static void searchBook(){
          
        String seachKey ;
        Scanner choose = new Scanner(System.in);
            
        System.out.println();
        System.out.print("Enter Book ID : ");
        seachKey = choose.nextLine();
        System.out.println();
           
        //read all that have chosen category
        String filepath = "book.txt";
        
        try {
            Scanner filebook = new Scanner(new File(filepath));
            
            while(filebook.hasNextLine()){
                
                String line = filebook.nextLine();
                String[] split = line.split(",");
                
                if(split[2].contains(seachKey)){
                    System.out.println("Book Name : " + split[0]);
                    System.out.println("Book Author : " + split[1]);
                    System.out.println("Book ID : " + split[2]);
                    System.out.println("Book Category : " + split[3]);
                    System.out.println("Book Location : " + split[4]);
                    System.out.println();
                }
            }
           
           filebook.close();
           Menu();
           
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void viewBorrow(){
  
        String borrowUser ;
        Scanner choose = new Scanner(System.in);
            
        System.out.println();
        System.out.print("Enter Name : ");
        borrowUser = choose.nextLine();
        System.out.println();
           
        //read all that have chosen category
        String filepath = "borrow.txt";
        
        try {
            Scanner fileborrow = new Scanner(new File(filepath));
            
            while(fileborrow.hasNextLine()){
                
                String line = fileborrow.nextLine();
                String[] split = line.split(",");
                
                if(split[0].contains(borrowUser)){
                    System.out.println("Book ID : " + split[3]);
                    System.out.println("Date Return : " + split[4]);
                    System.out.println();
                }
            }
           
           fileborrow.close();
           Menu();
           
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
