package chapter6;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class UpdatePhoneBill implements Serializable
{
    static ArrayList<PhoneBill> accounts = new ArrayList<>();
    static ArrayList<Plans> mobilePlans = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);
    static int customerPin = 123;
    static int billerPin = 456;
    static int adminPin = 789;

    public static void main(String[] args) {


        try{
           FileInputStream readData = new FileInputStream("StorePhoneBills.txt");
           try (ObjectInputStream readStream = new ObjectInputStream(readData)) {

               accounts = (ArrayList<PhoneBill>) readStream.readObject();

           }
        }catch (Exception e) {
            e.printStackTrace();
        }

        try{
             FileInputStream readData = new FileInputStream("AvailablePlans.txt");
            try (ObjectInputStream readStream = new ObjectInputStream(readData)) {

                mobilePlans = (ArrayList<Plans>) readStream.readObject();

            }
        }catch (Exception e) {
            e.printStackTrace();
        }

        MainMenu();
        System.out.println("Have a good day. All changes have been updated.");

        try{
            FileOutputStream writeData = new FileOutputStream("StorePhoneBills.txt");
            ObjectOutputStream writeStream = new ObjectOutputStream(writeData);

            writeStream.writeObject(accounts);
            writeStream.flush();
            writeStream.close();

        }catch (IOException e) {
            e.printStackTrace();
        }

        try{
            FileOutputStream writeData = new FileOutputStream("AvailablePlans.txt");
            ObjectOutputStream writeStream = new ObjectOutputStream(writeData);

            writeStream.writeObject(mobilePlans);
            writeStream.flush();
            writeStream.close();

        }catch (IOException e) {
            e.printStackTrace();
        }

    }


    /*
        Determines who is using the system and checks their credentials.
    */
    public static void MainMenu() {
        //Ask what the user wants to do, then ask if they would like to continue
        int task;
        boolean invalidEntry = true;
        int enteredPin;
        System.out.println("Welcome to our cellphone system. Are you an account holder, biller or admin?");
        do {
            System.out.println("Account holder press 1");
            System.out.println("Biller press 2");
            System.out.println("Admin press 3");
            task = scanner.nextInt();

            switch (task) {
                case 1:
                    System.out.println("Please enter the pin");
                    enteredPin = scanner.nextInt();
                    if (CheckPin(enteredPin,customerPin)){
                        CustomerMenu();
                        invalidEntry = false;
                        break;
                    }
                    else{
                        System.out.println("invalid pin, please try again");
                    }
                    System.out.println("A");
                    break;
                case 2:
                    System.out.println("Please enter the pin");
                    enteredPin = scanner.nextInt();
                    if (CheckPin(enteredPin,billerPin)){
                        BillerMenu();
                        invalidEntry = false;
                        break;
                    }
                    else{
                        System.out.println("invalid pin, please try again");
                    }
                    System.out.println("B");
                    break;
                case 3:
                    System.out.println("Please enter the pin");
                    enteredPin = scanner.nextInt();
                    if (CheckPin(enteredPin,adminPin)){
                        AdminMenu();
                        invalidEntry = false;
                        break;
                    }
                    else{
                        System.out.println("invalid pin, please try again");
                    }
                    System.out.println("C");
                    break;
                default:
                    System.out.println("Invalid option, please try again");
                    break;
            }

        }while (invalidEntry == true);

    }
    private static void CustomerMenu() {
        int task;

        System.out.println("What is your account ID:");
        int accountID = scanner.nextInt();

        boolean validID = false;

        for (int i = 0; i < accounts.size(); i++) {
            if (accountID == accounts.get(i).getId()) {
                validID = true;
                System.out.println("Thank you. That plan shows:");
                accounts.get(i).printBill();

                do {
                    System.out.println("What would you like to do:");
                    System.out.println("To update your current phone plan press 1.");
                    System.out.println("To update your minutes used press 2.");
                    System.out.println("To finish press 3.");
                    task = scanner.nextInt();

                    switch (task) {
                        case 1:
                            boolean validPlan = false;
                            System.out.println("What is the new plan type: ");
                            String newPlan = scanner.next();
                            for (int j = 0; j < mobilePlans.size(); j++) {
                                if (newPlan.equalsIgnoreCase(mobilePlans.get(j).getPlanId())) {
                                    validPlan = true;
                                    accounts.get(i).setPlan(newPlan);
                                    accounts.get(i).setMinutesAllowed(mobilePlans.get(j).getAllowedMinutes());
                                    accounts.get(i).setPlanPrice(mobilePlans.get(j).getPlanPrice());
                                    break;
                                }
                            }
                            if (validPlan == false) {
                                System.out.println("The plan does not exist. Please try again");
                                break;
                            }
                            break;
                        case 2:
                            System.out.println("How many minutes were used: ");
                            int newMinutesUsed = scanner.nextInt();
                            accounts.get(i).setMinutesUsed(newMinutesUsed);
                            break;

                    }
                }while (task != 3);
            }
        }
        if (validID == false) {
            System.out.println("ID not found.Please try again.");
            System.out.println("");
        }
    }
    public static void BillerMenu() {
        int task;

        do {
            System.out.println("What would you like to do:");
            System.out.println("To update a current phone account press 1.");
            System.out.println("To print a current phone account press 2.");
            System.out.println("To enter a new phone account press 3.");
            System.out.println("To finish press 4.");
            task = scanner.nextInt();

            //Works option chosen
            switch (task) {
                case 1:
                    //update current
                    System.out.println("Please enter the account ID you would like to update:");
                    int updateID = scanner.nextInt();
                    boolean validID = false;

                    for (int i = 0; i < accounts.size(); i++) {
                        if (updateID == accounts.get(i).getId()) {
                            validID = true;
                            System.out.println("Thank you. That plan shows:");
                            accounts.get(i).printBill();
                            System.out.println("");
                            System.out.println("What would you like to change:");
                            System.out.println("To update the account ID press 1.");
                            System.out.println("To update the plan type press 2.");
                            System.out.println("To update the minutes used press 4.");
                            int option = scanner.nextInt();
                            switch (option) {
                                case 1:
                                    System.out.println("What is the new account ID: ");
                                    int newID = scanner.nextInt();
                                    for (int m = 0; m < accounts.size(); m++) {
                                        if (newID == accounts.get(m).getId()) {
                                            System.out.println("That account ID already exist, please try again.");
                                            break;
                                        }
                                    }
                                    accounts.get(i).setId(newID);
                                    break;

                                case 2:
                                    boolean validPlan = false;
                                    System.out.println("What is the new plan type: ");
                                    String newPlan = scanner.next();
                                    for (int j = 0; j < mobilePlans.size(); j++) {
                                        if (newPlan.equalsIgnoreCase(mobilePlans.get(j).getPlanId())) {
                                            accounts.get(i).setPlan(newPlan);
                                            accounts.get(i).setMinutesAllowed(mobilePlans.get(j).getAllowedMinutes());
                                            accounts.get(i).setPlanPrice(mobilePlans.get(j).getPlanPrice());
                                            validPlan = true;
                                            break;
                                        }
                                    }
                                    if (validPlan == false) {
                                        System.out.println("The plan does not exist. Please try again");
                                        break;
                                    }

                                case 4:
                                    System.out.println("How many minutes were used: ");
                                    int newMinutesUsed = scanner.nextInt();
                                    accounts.get(i).setMinutesUsed(newMinutesUsed);
                                    break;
                            }
                            System.out.println("That plan now shows:");
                            accounts.get(i).printBill();
                            System.out.println("");
                            break;
                        }
                    }
                    if (validID == false) {
                        System.out.println("ID not found.Please try again.");
                        System.out.println("");
                    }
                    break;
                case 2:
                    //print account
                    System.out.println("What is the ID of the account you would like to print:");
                    int printID = scanner.nextInt();
                    boolean checkID = false;

                    for (int l = 0; l < accounts.size(); l++) {
                        if (printID == accounts.get(l).getId()) {
                            accounts.get(l).printBill();
                            checkID = true;
                            break;
                        }
                    }
                    if (checkID == false) {
                        System.out.println("ID not found.Please try again.");
                    }
                    break;

                case 3:
                    //add account
                    System.out.println("What is the new ID:");
                    int newID = scanner.nextInt();
                    validID = false;

                    for (int i = 0; i < accounts.size(); i++) {
                        if (newID == accounts.get(i).getId()) {
                            validID = true;
                            System.out.println("That account already exist. please try again.");
                            break;

                        }
                    }

                    if (validID == false){
                        boolean validPlan = false;
                        System.out.println("What is the plan ID:");
                        String newPlan = scanner.next();
                        for (int j = 0; j < mobilePlans.size(); j++) {
                            if (newPlan.equalsIgnoreCase(mobilePlans.get(j).getPlanId())) {
                                validPlan = true;
                                System.out.println("How many minutes were used: ");
                                int newMinutesUsed = scanner.nextInt();
                                PhoneBill newAccount = new PhoneBill(newID, newPlan, mobilePlans.get(j).getPlanPrice(), newMinutesUsed, mobilePlans.get(j).getAllowedMinutes());
                                accounts.add(newAccount);
                                System.out.println("New account added.");
                                break;
                            }
                        }
                        if (validPlan == false) {
                                System.out.println("The plan does not exist. Please try again");
                                break;
                            }
                    }

                case 4:
                    //Exit and write to the file
                    break;
                default:
                    System.out.println("Invalid response, please try again B");
                }
        } while (task != 4);
    }


    public static void AdminMenu() {
        int task;

        do {
            System.out.println("What would you like to do:");
            System.out.println("Enter billing system 1.");
            System.out.println("Update mobile plans 2.");
            System.out.println("Create a new mobile plan 3.");
            System.out.println("To finish press 4.");
            task = scanner.nextInt();

            //Works option chosen
            switch (task) {
                case 1:
                    BillerMenu();
                    break;
                case 2:
                    boolean validPlan = false;
                    System.out.println("Which plan would you like to update: ");
                    String newPlan = scanner.next();
                    for (int j = 0; j < mobilePlans.size(); j++) {
                        if (newPlan.equalsIgnoreCase(mobilePlans.get(j).getPlanId())) {
                            validPlan = true;
                            System.out.println("Which would you like to update:");
                            System.out.println("1. Minutes allowed");
                            System.out.println("2. Price");
                            int planUpdateOption = scanner.nextInt();
                            switch (planUpdateOption){
                                case 1:
                                    System.out.println("Enter the new allotted minutes for this plan:");
                                    int newMinutes = scanner.nextInt();
                                    mobilePlans.get(j).setAllowedMinutes(newMinutes);
                                    System.out.println("new minutes" + mobilePlans.get(j).getAllowedMinutes());

                                    break;
                                case 2:
                                    System.out.println("Enter the new price, for this plan:");
                                    double newPrice = scanner.nextDouble();
                                    mobilePlans.get(j).setPrice(newPrice);
                                    System.out.println("new price" + mobilePlans.get(j).getPlanPrice());

                                    break;
                            }

                            System.out.println("");
                        }
                    }
                    if (validPlan == false) {
                        System.out.println("Invalid plan entered. Please try again");
                        break;
                    }
                    break;

                case 3:
                    //add a new plan
                    System.out.println("What is the new Plan ID:");
                    String newID = scanner.next();
                    System.out.println("What is the base plan cost:");
                    double newPrice = scanner.nextDouble();
                    System.out.println("What is the allowed minutes:");
                    int newAllowedMinutes = scanner.nextInt();
                    Plans newAccount = new Plans(newID, newAllowedMinutes, newPrice);
                    mobilePlans.add(newAccount);
                    System.out.println("New plan added.");
                    System.out.println("");
                    break;

                case 4:
                    //Exit and write to the file
                    break;
                default:
                    System.out.println("Invalid response, please try again B");
            }
        } while (task != 4);

    }

    public static boolean CheckPin (int enteredPin, int correctPin) {

        if (enteredPin == correctPin)
            return true;
        else return false;
    }
}
