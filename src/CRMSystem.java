import com.opencsv.CSVReader;

import java.io.*;
import java.util.Scanner;

public class CRMSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        File file = new File("textfile.csv");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        Service service = null;
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(file);
            CSVReader reader = new CSVReader(fileReader);
            service = new ClientService(reader);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Implement file operations for XML/SQLite
        Manager manager = new ClientManager(service);
        System.out.println("Welcome to the Client Management System");
        displayOptions();
        boolean active = true;
        while (active) {
            String command = scanner.nextLine();
            if (command.equals("Save & Exit")) {
                try {
                    service.saveToFile();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                active = false;
            } else {
                manager.performAction(command);
            }
            // Add Client
            // 1, Oceanic Enterprises, Finance, Sarah Smith, 500000.00
            // Update Client
            // 1, Oceanic Enterprises, Tech, Sarah Smith, 750000.00
            // View Clients
            // Search Industry Tech
            // Search ID 1
            // Remove Client 1
            // Search Name Oceanic
            // Save & Exit
        }
    }

    public static void displayOptions() {
        System.out.println("1. Add Client");
        System.out.println("2. Update Client");
        System.out.println("3. Remove Client");
        System.out.println("4. Search ");
        System.out.println("5. View Clients");
        System.out.println("6. Save & Exit");
    }
}