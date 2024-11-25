package com.atmsimulation;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;


public class ATMSimulationMain {
    public enum Screen {
        WELCOME_SCREEN, TRANSACTION_SCREEN, WITHDRAW_SCREEN, OTHER_WITHDRAW_SCREEN, SUMMARY_SCREEN, EXIT_SCREEN,
        FUND_TRANSFER_STEP1_SCREEN, FUND_TRANSFER_STEP2_SCREEN, FUND_TRANSFER_STEP3_SCREEN, FUND_TRANSFER_STEP4_SCREEN,
        FUND_TRANSFER_SUMMARY_SCREEN
    }

    static List<Account> accountList = init();
    static Account account = null;
    static int withdrawAmount = 0;
    static FundTransfer fundTransfer = null;
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        // Initialize
        Screen displayScreen = Screen.WELCOME_SCREEN;
        do{
            switch (displayScreen) {
                case WELCOME_SCREEN -> displayScreen = welcomeScreen();
                case TRANSACTION_SCREEN -> displayScreen = transactionScreen();
                case WITHDRAW_SCREEN -> displayScreen = withdrawScreen();
                case OTHER_WITHDRAW_SCREEN -> displayScreen = otherWithdrawScreen();
                case SUMMARY_SCREEN -> displayScreen = summaryScreen();
                case FUND_TRANSFER_STEP1_SCREEN -> displayScreen = fundTransferStep1Screen();
                case FUND_TRANSFER_STEP2_SCREEN -> displayScreen = fundTransferStep2Screen();
                case FUND_TRANSFER_STEP3_SCREEN -> displayScreen = fundTransferStep3Screen();
                case FUND_TRANSFER_STEP4_SCREEN -> displayScreen = fundTransferStep4Screen();
                case FUND_TRANSFER_SUMMARY_SCREEN -> displayScreen = fundTransferSummaryScreen();
                case EXIT_SCREEN -> exitScreen();
            }
        } while(displayScreen != Screen.EXIT_SCREEN);

    }

    public static List<Account> init() {
        List<Account> accountList = new ArrayList<>();
        accountList.add(new Account("John Doe", "012108", 100, "112233"));
        accountList.add(new Account("Jane Doe", "932012", 30, "112244"));

        return accountList;
    }

    static Screen welcomeScreen() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter account number: ");
        String accountNumber = sc.next();
        System.out.println("Enter PIN: ");
        String pin = sc.next();

        // authentication
        boolean isValidAuth = authentication(accountNumber, pin);
        if(isValidAuth) {
            System.out.println("Account successfully authenticated");
            return Screen.TRANSACTION_SCREEN;
        } else {
            System.out.println("Invalid Account Number/PIN");
            return Screen.WELCOME_SCREEN;
        }
    }

    static Screen transactionScreen() {
        Scanner sc = new Scanner(System.in);

        System.out.println("1. Withdraw");
        System.out.println("2. Fund Transfer");
        System.out.println("3. Exit");
        System.out.println("Please choose option[3]:");
        int option = sc.nextInt();

        return switch (option) {
            case 1 -> Screen.WITHDRAW_SCREEN;
            case 2 -> Screen.FUND_TRANSFER_STEP1_SCREEN;
            default -> Screen.EXIT_SCREEN;
        };
    }

    static Screen withdrawScreen() {
        int nominal1 = 10;
        int nominal2 = 50;
        int nominal3 = 100;
        Scanner sc = new Scanner(System.in);

        System.out.println("1. $" + nominal1);
        System.out.println("2. $" + nominal2);
        System.out.println("3. $" + nominal3);
        System.out.println("4. Other");
        System.out.println("5. Back");
        System.out.println("Please choose option[5]:");
        int option = sc.nextInt();

        switch (option){
            case 1, 2, 3 -> {
                // Deduct user balance
                int balance = account.getBalance();
                if(option == 1) {
                    balance = balance - nominal1;
                    withdrawAmount = nominal1;
                } else if(option == 2) {
                    balance = balance - nominal2;
                    withdrawAmount = nominal2;
                } else if(option == 3) {
                    balance = balance - nominal3;
                    withdrawAmount = nominal3;
                }

                if(balance < 0) {
                    System.out.println("Insufficient balance $"+account.getBalance());
                    return Screen.WITHDRAW_SCREEN;
                }
                else {
                    account.setBalance(balance);
                }
                System.out.println("balance after withdraw:"+account.getBalance());
                return Screen.SUMMARY_SCREEN;
            }
            case 4 -> {
                return Screen.WITHDRAW_SCREEN;
            }
            default -> {
                return Screen.TRANSACTION_SCREEN;
            }

        }

    }

    static Screen otherWithdrawScreen() {

        System.out.println("Other Withdraw");
        System.out.println("Enter amount to withdraw:");
        Scanner sc = new Scanner(System.in);
        int amount = sc.nextInt();

        if(amount > 1000) {
            System.out.println("Maximum amount to withdraw is $1000");
            return Screen.OTHER_WITHDRAW_SCREEN;
        } else if (amount % 10 > 0){
            System.out.println("Invalid ammount");
            return Screen.OTHER_WITHDRAW_SCREEN;
        } else if (amount > account.getBalance()) {
            System.out.println("Insufficient balance $" + account.getBalance());
            return Screen.OTHER_WITHDRAW_SCREEN;
        }

        int currentBalance = account.getBalance() - amount;
        account.setBalance(currentBalance);

        return Screen.SUMMARY_SCREEN;

    }

    static Screen summaryScreen() {
        String dateDisplay = LocalDate.now() + " " + LocalTime.now();

        System.out.println("Summary");
        System.out.println("Date : " + dateDisplay);
        System.out.println("Withdraw : $" + withdrawAmount);
        System.out.println("Balance : $" + account.getBalance());
        System.out.println("1. Transaction");
        System.out.println("2. Exit");
        System.out.println("Choose option[2]:");
        Scanner sc = new Scanner(System.in);
        int option = sc.nextInt();

        withdrawAmount = 0;

        return option == 1 ? Screen.TRANSACTION_SCREEN : Screen.EXIT_SCREEN;
    }

    static Screen fundTransferStep1Screen() {
        System.out.println("Please enter destination account and press enter to continue or");
        System.out.println("press cancel (Esc) to go back to Transaction:");
        String beneficiaryAccount = sc.next();

        fundTransfer = new FundTransfer();
        fundTransfer.setBeneficiaryAccount(beneficiaryAccount);

         return Screen.FUND_TRANSFER_STEP2_SCREEN;
    }

    static Screen fundTransferStep2Screen() {
        System.out.println("Please enter transfer amount and press enter to continue or");
        System.out.println("press enter to go back to Transaction:");
        String amountTransfer = sc.next();

        fundTransfer.setAmount(amountTransfer);

        return Screen.FUND_TRANSFER_STEP3_SCREEN;
    }

    static Screen fundTransferStep3Screen() {
        Random rand = new Random();
        int referenceNumber = rand.nextInt(999999);
        System.out.println("Reference Number: " + String.format("%06d", referenceNumber));
        System.out.println("press enter to continue or press enter to go back to Transaction:");

        fundTransfer.setReferenceNumber(String.valueOf(referenceNumber));

        return Screen.FUND_TRANSFER_STEP4_SCREEN;
    }

    static Screen fundTransferStep4Screen() {
        System.out.println("Transfer Confirmation");
        System.out.println("Destination Account : " + fundTransfer.getBeneficiaryAccount());
        System.out.println("Transfer Amount     : $" + fundTransfer.getAmount());
        System.out.println("Reference Number    : " + fundTransfer.getReferenceNumber());
        System.out.println("\r\n1. Confirm Trx");
        System.out.println("2. Cancel Trx");
        System.out.println("Choose option[2]:");
        int option = sc.nextInt();
        boolean isValidTransaction = false;

        if(option == 1) {
            // validate bene acc
            validateFundTransferAmount();
        }

        return isValidTransaction ? Screen.FUND_TRANSFER_SUMMARY_SCREEN : Screen.TRANSACTION_SCREEN;
    }

    static boolean validateFundTransferAmount() {
        boolean isValidTransaction = false;

        if(!fundTransfer.getBeneficiaryAccount().matches("[0-9]+")) System.out.println("Invalid account");
        else if(!Account.validateAccountNumber(fundTransfer.getBeneficiaryAccount())) System.out.println("Invalid account");
        else if(Integer.parseInt(fundTransfer.getAmount()) > 1000)  System.out.println("Maximum amount to transfer is $1000");
        else if(Integer.parseInt(fundTransfer.getAmount()) < 1) System.out.println("Minimum amount to transfer is $1");
        else if(!fundTransfer.getAmount().matches("[0-9]+")) System.out.println("Invalid amount");
        else if((account.getBalance() < Integer.parseInt(fundTransfer.getAmount()))) System.out.println("Insufficient balance");
        else {
            Account destAccount = findAccountByAccountNumber(fundTransfer.getBeneficiaryAccount());
            if(destAccount != null) {
                doTransaction(destAccount);
            }

            isValidTransaction = true;
        }

        return isValidTransaction;
    }

    static void doTransaction(Account destAccount) {
        // Deducted source account balance
        account.setBalance(account.getBalance() - Integer.parseInt(fundTransfer.getAmount()));

        // Credit to destination account
        destAccount.setBalance(destAccount.getBalance() + Integer.parseInt(fundTransfer.getAmount()));

        // Update account
        updateAccount(destAccount);
    }

    static Screen fundTransferSummaryScreen() {

        System.out.println("Fund Transfer Summary");
        System.out.println("Destination Account : " + fundTransfer.getBeneficiaryAccount());
        System.out.println("Transfer Amount     : $" + fundTransfer.getAmount());
        System.out.println("Reference Number    : " + fundTransfer.getReferenceNumber());
        System.out.println("Balance             : $" + account.getBalance());
        System.out.println("1. Transaction");
        System.out.println("2. Exit");
        System.out.println("Choose option[2]:");
        Scanner sc = new Scanner(System.in);
        int option = sc.nextInt();

        return option == 1 ? Screen.TRANSACTION_SCREEN : Screen.EXIT_SCREEN;
    }

    public static void  exitScreen() {
        System.out.println("Thank you");
        System.exit(0);
    }

    public static boolean authentication(String accountNumber, String pin) {

        boolean isValidAccountNumber = Account.validateAccountNumber(accountNumber);
        boolean isValidPin = Account.validatePin(pin);
        boolean isValidAccNumAndPin = false;
        for(Account acc : accountList) {
            if(acc.getAccountNumber().equals(accountNumber) && acc.getPin().equals(pin)) {
                isValidAccNumAndPin = true;
                account = acc;
                System.out.println("LOG");
                System.out.println("account number:" + account.getAccountNumber());
                System.out.println("account name:" + account.getName());
                System.out.println("balance:" + account.getBalance());
            }
        }

        return isValidAccountNumber && isValidPin && isValidAccNumAndPin;
    }

    public static Account findAccountByAccountNumber(String accountNumber) {
        for(Account acc : accountList) {
            if(acc.getAccountNumber().equals(accountNumber)) {
                return acc;
            }
        }

        return null;
    }

    public static void updateAccount(Account updatedAccount) {
        accountList.replaceAll(acc -> acc.getAccountNumber() == updatedAccount.getAccountNumber() ? updatedAccount : acc);
    }
}
