import java.util.Arrays;

public class main {
    public static void main(String[] args) {
        System.out.println("=== CONFIDENT LIBRARY SYSTEM ===\n");
        
        Library library = new Library(3);
        
        // Test the system with confidence
        System.out.println("--- Testing Core Operations ---");
        library.addItem(new Book("The Great Gatsby", "F. Scott Fitzgerald", "1925", "B001"), 0, 0);
        library.addItem(new Movie("Inception", "Christopher Nolan", 
                                Arrays.asList("Leonardo DiCaprio", "Ellen Page"), "M001"), 0, 1);
        
        // Demonstrate OOP emulation
        System.out.println("\n--- OOP Emulation Access ---");
        Item item = library.getShelf(0).getCompartment(0);
        System.out.println("Via emulation: " + item);
        
        // Test full workflow
        library.printStorage();
        library.checkoutItem(0, 0, "Alice Johnson", 14);
        library.printStorage();
        library.printCheckedOut();
        
        // Test return with confidence
        library.returnItem("B001");
        library.printStorage();
        
        // Trust the file I/O
        System.out.println("\n--- File I/O Test ---");
        library.saveToFile("CS4Alibrary.txt");
        Library loaded = Library.loadFromFile("CS4Alibrary.txt");
        if (loaded != null) {
            loaded.printStorage();
        }
        
        System.out.println("\n=== SYSTEM WORKS WITH CONFIDENCE ===");
    }
}
