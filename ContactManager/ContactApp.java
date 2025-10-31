import java.util.*;
import java.io.*;

public class ContactApp {
    
    private static ArrayList<Contact> contacts = new ArrayList<>();
    private static Stack<Contact> deletedContacts = new Stack<>();
    private static Scanner scanner = new Scanner(System.in);
    private static final String FILE = "contacts.dat";
    
    public static void main(String[] args) {
        loadContacts();
        
        System.out.println("\n" + "=".repeat(65));
        System.out.println("     ADVANCED CONTACT MANAGEMENT SYSTEM");
        System.out.println("=".repeat(65));
        
        while (true) {
            showMenu();
            int choice = getChoice();
            
            if (choice == 0) break;
            handleChoice(choice);
            
            System.out.println("\nPress Enter to continue...");
            scanner.nextLine();
        }
        
        System.out.println("\n" + "=".repeat(65));
        System.out.println("     Thanks for using! All data saved automatically.");
        System.out.println("=".repeat(65) + "\n");
    }
    
    private static void showMenu() {
        System.out.println("\n" + "-".repeat(65));
        System.out.println("MENU:");
        System.out.println("  1. Add Contact          7. Toggle Priority");
        System.out.println("  2. View All             8. Filter by Category");
        System.out.println("  3. Search               9. Sort Contacts");
        System.out.println("  4. View Details        10. Undo Delete");
        System.out.println("  5. Update              11. Export CSV");
        System.out.println("  6. Delete              12. Statistics");
        System.out.println("  0. Exit");
        System.out.println("-".repeat(65));
        System.out.print("Choose: ");
    }
    
    private static void handleChoice(int choice) {
        switch (choice) {
            case 1: addContact(); break;
            case 2: viewAll(); break;
            case 3: searchContacts(); break;
            case 4: viewDetails(); break;
            case 5: updateContact(); break;
            case 6: deleteContact(); break;
            case 7: togglePriority(); break;
            case 8: filterByCategory(); break;
            case 9: sortContacts(); break;
            case 10: undoDelete(); break;
            case 11: exportCSV(); break;
            case 12: showStats(); break;
            default: System.out.println("Invalid choice!");
        }
    }
    
    private static void addContact() {
        System.out.println("\n--- ADD NEW CONTACT ---");
        
        System.out.print("Name: ");
        String name = scanner.nextLine().trim();
        
        String phone = "";
        while (true) {
            System.out.print("Phone (10 digits): ");
            phone = scanner.nextLine().trim();
            if (phone.matches("\\d{10}")) {
                break;
            }
            System.out.println("Invalid! Enter 10 digits.");
        }
        
        String email = "";
        while (true) {
            System.out.print("Email: ");
            email = scanner.nextLine().trim();
            if (email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
                break;
            }
            System.out.println("Invalid email!");
        }
        
        System.out.println("\nCategory: 1)Work 2)Personal 3)Family 4)Friends 5)Other");
        System.out.print("Choose (1-5): ");
        int cat = getChoice(1, 5);
        String[] categories = {"Work", "Personal", "Family", "Friends", "Other"};
        
        Contact c = new Contact(name, phone, email, categories[cat-1]);
        
        System.out.print("Add notes (optional): ");
        String notes = scanner.nextLine().trim();
        if (!notes.isEmpty()) {
            c.setNotes(notes);
        }
        
        contacts.add(c);
        saveContacts();
        System.out.println("\nContact added! ID: " + c.getId());
    }
    
    private static void viewAll() {
        if (contacts.isEmpty()) {
            System.out.println("\nNo contacts found!");
            return;
        }
        
        System.out.println("\n" + "=".repeat(80));
        System.out.println("ALL CONTACTS (" + contacts.size() + " total)");
        System.out.println("=".repeat(80));
        
        for (int i = 0; i < contacts.size(); i++) {
            System.out.printf("%3d. %s\n", i+1, contacts.get(i).toDisplay());
        }
        System.out.println("=".repeat(80));
    }
    
    private static void searchContacts() {
        System.out.print("\nSearch (name/phone/email): ");
        String query = scanner.nextLine().trim();
        
        ArrayList<Contact> results = new ArrayList<>();
        for (Contact c : contacts) {
            if (c.matches(query)) {
                results.add(c);
            }
        }
        
        if (results.isEmpty()) {
            System.out.println("\nNo matches found!");
            return;
        }
        
        System.out.println("\n--- FOUND " + results.size() + " RESULT(S) ---");
        for (int i = 0; i < results.size(); i++) {
            System.out.println((i+1) + ". " + results.get(i).toDisplay());
        }
    }
    
    private static void viewDetails() {
        int idx = selectContact("View details of");
        if (idx == -1) return;
        
        System.out.println("\n" + "=".repeat(50));
        System.out.println(contacts.get(idx).toDetailedView());
        System.out.println("=".repeat(50));
    }
    
    private static void updateContact() {
        int idx = selectContact("Update");
        if (idx == -1) return;
        
        Contact c = contacts.get(idx);
        System.out.println("\n--- UPDATE: " + c.getName() + " ---");
        System.out.println("(Leave blank to keep current)");
        
        System.out.print("New name: ");
        String name = scanner.nextLine().trim();
        if (!name.isEmpty()) {
            c.setName(name);
        }
        
        System.out.print("New phone: ");
        String phone = scanner.nextLine().trim();
        if (!phone.isEmpty() && phone.matches("\\d{10}")) {
            c.setPhone(phone);
        }
        
        System.out.print("New email: ");
        String email = scanner.nextLine().trim();
        if (!email.isEmpty() && email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            c.setEmail(email);
        }
        
        System.out.print("Update notes: ");
        String notes = scanner.nextLine().trim();
        if (!notes.isEmpty()) {
            c.setNotes(notes);
        }
        
        saveContacts();
        System.out.println("\nContact updated!");
    }
    
    private static void deleteContact() {
        int idx = selectContact("Delete");
        if (idx == -1) return;
        
        Contact c = contacts.get(idx);
        System.out.print("Confirm delete '" + c.getName() + "'? (yes/no): ");
        
        if (scanner.nextLine().trim().equalsIgnoreCase("yes")) {
            contacts.remove(idx);
            deletedContacts.push(c);
            saveContacts();
            System.out.println("\nDeleted! Use option 10 to undo.");
        } else {
            System.out.println("Cancelled.");
        }
    }
    
    private static void togglePriority() {
        int idx = selectContact("Toggle priority for");
        if (idx == -1) return;
        
        Contact c = contacts.get(idx);
        c.setPriority(!c.isPriority());
        saveContacts();
        
        String status = c.isPriority() ? "marked as PRIORITY" : "unmarked";
        System.out.println("\n" + c.getName() + " " + status + "!");
    }
    
    private static void filterByCategory() {
        System.out.println("\nCategory: 1)Work 2)Personal 3)Family 4)Friends 5)Other");
        System.out.print("Choose (1-5): ");
        int cat = getChoice(1, 5);
        String[] categories = {"Work", "Personal", "Family", "Friends", "Other"};
        String selected = categories[cat-1];
        
        System.out.println("\n--- " + selected.toUpperCase() + " CONTACTS ---");
        int count = 0;
        for (Contact c : contacts) {
            if (c.getCategory().equals(selected)) {
                System.out.println(c.toDisplay());
                count++;
            }
        }
        if (count == 0) {
            System.out.println("No contacts in this category.");
        }
    }
    
    private static void sortContacts() {
        System.out.println("\nSort: 1)Name(A-Z) 2)Name(Z-A) 3)Category 4)Priority");
        System.out.print("Choose (1-4): ");
        int choice = getChoice(1, 4);
        
        switch (choice) {
            case 1:
                contacts.sort(Comparator.comparing(Contact::getName));
                break;
            case 2:
                contacts.sort(Comparator.comparing(Contact::getName).reversed());
                break;
            case 3:
                contacts.sort(Comparator.comparing(Contact::getCategory));
                break;
            case 4:
                contacts.sort(Comparator.comparing(Contact::isPriority).reversed());
                break;
        }
        
        saveContacts();
        System.out.println("\nSorted!");
        viewAll();
    }
    
    private static void undoDelete() {
        if (deletedContacts.isEmpty()) {
            System.out.println("\nNothing to undo!");
            return;
        }
        
        Contact c = deletedContacts.pop();
        contacts.add(c);
        saveContacts();
        System.out.println("\nRestored: " + c.getName());
    }
    
    private static void exportCSV() {
        if (contacts.isEmpty()) {
            System.out.println("\nNo contacts to export!");
            return;
        }
        
        try {
            String filename = "contacts_" + System.currentTimeMillis() + ".csv";
            PrintWriter pw = new PrintWriter(filename);
            pw.println("Name,Phone,Email,Category,Priority,Notes");
            
            for (Contact c : contacts) {
                pw.printf("\"%s\",\"%s\",\"%s\",\"%s\",%s,\"%s\"\n",
                    c.getName(), c.getPhone(), c.getEmail(), c.getCategory(),
                    c.isPriority() ? "Yes" : "No", c.getNotes());
            }
            pw.close();
            System.out.println("\nExported to: " + filename);
        } catch (IOException e) {
            System.out.println("\nExport failed!");
        }
    }
    
    private static void showStats() {
        if (contacts.isEmpty()) {
            System.out.println("\nNo statistics available.");
            return;
        }
        
        Map<String, Integer> categoryCount = new HashMap<>();
        int priorityCount = 0;
        
        for (Contact c : contacts) {
            categoryCount.put(c.getCategory(), 
                categoryCount.getOrDefault(c.getCategory(), 0) + 1);
            if (c.isPriority()) {
                priorityCount++;
            }
        }
        
        System.out.println("\n" + "=".repeat(50));
        System.out.println("STATISTICS");
        System.out.println("=".repeat(50));
        System.out.println("Total Contacts: " + contacts.size());
        System.out.println("Priority Contacts: " + priorityCount);
        System.out.println("\nBy Category:");
        for (Map.Entry<String, Integer> entry : categoryCount.entrySet()) {
            System.out.println("  " + entry.getKey() + ": " + entry.getValue());
        }
        System.out.println("=".repeat(50));
    }
    
    private static void saveContacts() {
        try {
            PrintWriter pw = new PrintWriter(FILE);
            for (Contact c : contacts) {
                pw.println(c.toFileString());
            }
            pw.close();
        } catch (IOException e) {
            System.out.println("Error saving!");
        }
    }
    
    private static void loadContacts() {
        File f = new File(FILE);
        if (!f.exists()) return;
        
        try {
            BufferedReader br = new BufferedReader(new FileReader(f));
            String line;
            while ((line = br.readLine()) != null) {
                Contact c = Contact.fromFileString(line);
                if (c != null) {
                    contacts.add(c);
                }
            }
            br.close();
        } catch (IOException e) {
            System.out.println("Error loading!");
        }
    }
    
    private static int getChoice() {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (Exception e) {
            return -1;
        }
    }
    
    private static int getChoice(int min, int max) {
        while (true) {
            int choice = getChoice();
            if (choice >= min && choice <= max) {
                return choice;
            }
            System.out.print("Enter " + min + "-" + max + ": ");
        }
    }
    
    private static int selectContact(String action) {
        viewAll();
        if (contacts.isEmpty()) return -1;
        
        System.out.print("\n" + action + " contact number (0 to cancel): ");
        int num = getChoice();
        
        if (num == 0) return -1;
        if (num < 1 || num > contacts.size()) {
            System.out.println("Invalid number!");
            return -1;
        }
        return num - 1;
    }
}