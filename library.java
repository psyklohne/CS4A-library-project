import java.io.*;
import java.util.*;

public class Library implements Serializable {
    private List<Shelf> shelves;
    private List<CheckoutRecord> checkedOut;
    
    public Library(int numShelves) {
        shelves = new ArrayList<>();
        for (int i = 0; i < numShelves; i++) {
            shelves.add(new Shelf());
        }
        checkedOut = new ArrayList<>();
    }
    
    // OOP emulation access - trust the design
    public Shelf getShelf(int shelf) {
        if (shelf < 0 || shelf >= shelves.size()) {
            throw new IndexOutOfBoundsException("Invalid shelf: " + shelf);
        }
        return shelves.get(shelf);
    }
    
    // Direct access with clean error handling
    public Item getItem(int shelf, int compartment) {
        try {
            return getShelf(shelf).getCompartment(compartment);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }
    
    // Core operations - trusting our flow
    public boolean addItem(Item item, int shelf, int compartment) {
        try {
            Shelf targetShelf = getShelf(shelf);
            if (!targetShelf.isCompartmentEmpty(compartment)) {
                System.out.println("Error: Location already occupied");
                return false;
            }
            targetShelf.setCompartment(compartment, item);
            System.out.println("Added item to shelf " + shelf + ", compartment " + compartment);
            return true;
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }
    
    public boolean checkoutItem(int shelf, int compartment, String borrower, int dueDays) {
        try {
            Shelf targetShelf = getShelf(shelf);
            Item item = targetShelf.getCompartment(compartment);
            
            if (item == null) {
                System.out.println("Error: No item at this location");
                return false;
            }
            
            if (isItemCheckedOut(item.getId())) {
                System.out.println("Error: Item already checked out");
                return false;
            }
            
            // Confident creation - item is validated above
            CheckoutRecord record = new CheckoutRecord(item, borrower, dueDays, shelf, compartment);
            checkedOut.add(record);
            targetShelf.setCompartment(compartment, null);
            
            System.out.println("Checked out: " + item.getName() + " to " + borrower);
            return true;
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }
    
    public boolean returnItem(String itemId) {
        CheckoutRecord record = findCheckedOutRecord(itemId);
        if (record == null) {
            System.out.println("Error: Item not found in checked out items");
            return false;
        }
        
        // Confident access - item cannot be null in valid CheckoutRecord
        Item item = record.getItem();
        
        try {
            getShelf(record.getShelf()).setCompartment(record.getCompartment(), item);
            checkedOut.remove(record);
            System.out.println("Returned: " + item.getName() + " to shelf " + 
                              record.getShelf() + ", compartment " + record.getCompartment());
            return true;
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }
    
    public boolean swapItems(int shelf1, int compartment1, int shelf2, int compartment2) {
        try {
            Shelf shelfA = getShelf(shelf1);
            Shelf shelfB = getShelf(shelf2);
            
            Item item1 = shelfA.getCompartment(compartment1);
            Item item2 = shelfB.getCompartment(compartment2);
            
            if (item1 == null || item2 == null) {
                System.out.println("Error: Both compartments must contain items to swap");
                return false;
            }
            
            shelfA.setCompartment(compartment1, item2);
            shelfB.setCompartment(compartment2, item1);
            
            System.out.println("Swapped items between (" + shelf1 + "," + compartment1 + 
                              ") and (" + shelf2 + "," + compartment2 + ")");
            return true;
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }
    
    public void printStorage() {
        System.out.println("\n=== ITEMS IN STORAGE ===");
        boolean foundAny = false;
        
        for (int s = 0; s < shelves.size(); s++) {
            shelves.get(s).displayContents(s);
            for (int c = 0; c < shelves.get(s).getCompartmentCount(); c++) {
                if (!shelves.get(s).isCompartmentEmpty(c)) {
                    foundAny = true;
                    break;
                }
            }
        }
        
        if (!foundAny) {
            System.out.println("No items in storage.");
        }
    }
    
    public void printCheckedOut() {
        System.out.println("\n=== CHECKED OUT ITEMS ===");
        
        if (checkedOut.isEmpty()) {
            System.out.println("No items checked out.");
            return;
        }
        
        for (CheckoutRecord record : checkedOut) {
            Item item = record.getItem();  // Confident access
            System.out.println(item.getName() + " (ID: " + item.getId() + ")");
            System.out.println("  Borrowed by: " + record.getBorrower());
            System.out.println("  Due in: " + record.getDueDays() + " days");
            System.out.println("  From: Shelf " + record.getShelf() + 
                             ", Compartment " + record.getCompartment() + "\n");
        }
    }
    
    // File I/O - trusting Java's serialization
    public void saveToFile(String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(this);
            System.out.println("Library saved to " + filename);
        } catch (IOException e) {
            System.out.println("Error saving library: " + e.getMessage());
        }
    }
    
    public static Library loadFromFile(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            Library library = (Library) ois.readObject();
            System.out.println("Library loaded from " + filename);
            return library;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading library: " + e.getMessage());
            return null;
        }
    }
    
    // Helper methods - clean and confident
    private boolean isItemCheckedOut(String itemId) {
        return findCheckedOutRecord(itemId) != null;
    }
    
    private CheckoutRecord findCheckedOutRecord(String itemId) {
        for (CheckoutRecord record : checkedOut) {
            if (record.getItem().getId().equals(itemId)) {
                return record;
            }
        }
        return null;
    }
}
