import java.util.Arrays;

public class main {
    public static void main(String[] args) {
        System.out.println("===LIBRARY SYSTEM ===\n");
        
        Library library = new Library(3);
        
        System.out.println("--- Success Testing---");
        library.addItem(new Book("The Great Gatsby", "F. Scott Fitzgerald", "1925", "B001"), 0, 0);
        library.addItem(new Movie("Inception", "Christopher Nolan", 
                                Arrays.asList("Leonardo DiCaprio", "Ellen Page"), "M001"), 0, 1);
        library.addItem(new Magazine("Tech Review", "January", "AI Breakthrough", "MG001"), 1, 0);
        // Demonstrate polymorphic design with output overloading
        Item item = library.getShelf(0).getCompartment(0);
        System.out.println("Item: " + item);
        
        // Test item ownership transition 
        library.printStorage();
        library.checkoutItem(0, 0, "Alex Chash", 14); // Check out Book
        library.printStorage();
        library.printCheckedOut();

        // Test returning ownership
        library.returnItem("B001");
        library.printStorage();

        // Test successful swap
        System.out.println("Testing successful swap:");
        library.addItem(new Book("Swap Book A", "Author A", "2023", "B003"), 1, 1);
        library.addItem(new Book("Swap Book B", "Author B", "2023", "B004"), 1, 2);
        library.swapItems(1, 1, 1, 2);
        library.printStorage();

        // Test multiple checkouts
        System.out.println("Testing multiple checkouts:");
        library.checkoutItem(0, 1, "Zach Ziggy", 7);      // Checkout movie
        library.checkoutItem(1, 0, "Logan Tundra", 14); // Checkout magazine
        library.printCheckedOut();

        // Test independent container info, after only returning one item
        System.out.println("Returning one item:");
        library.returnItem("M001");
        library.printStorage();
        library.printCheckedOut();

        // File I/O
        System.out.println("\n--- File I/O Test ---");
        library.saveToFile("CS4Alibrary.bin");
        Library loaded = Library.loadFromFile("CS4Alibrary.bin");
        if (loaded != null) {
            loaded.printStorage();
        }
        
        // ============ EDGE CASE TESTING ============
        System.out.println("\n\n--- Failure Testing ---");

        // Test invalid location access
        System.out.println("\nTesting invalid location:");
        library.getItem(999, 999);  // Should show "Error: Invalid shelf: 999"

        // Test empty compartment operations  
        System.out.println("\nTesting empty compartment checkout:");
        library.checkoutItem(0, 14, "Test Student", 7);  // Should show "Error: No item in this compartment"

        // Test invalid check-in
        System.out.println("\nTesting invalid check-in:");
        library.returnItem("INVALID");  // Should show "Error: No checked-out item recorded for location"

        // Test empty swap
        System.out.println("\nTesting empty swap:");
        library.swapItems(0, 13, 0, 14);  // Should show "Error: Both compartments must contain items to swap"

        // Test duplicate checkout prevention
        System.out.println("\nTesting duplicate checkout prevention:");
        library.addItem(new Book("Duplicate Test", "Author", "2023", "B002"), 0, 2);
        System.out.println("   First checkout (should succeed):");
        library.checkoutItem(0, 2, "Guy", 14);  
        System.out.println("   Second checkout attempt (should fail):");
        library.checkoutItem(0, 2, "Man", 7);     // Should show "Error: No item in this compartment"

        // Test adding to occupied compartment
        System.out.println("\nTesting add to occupied compartment:");
        library.addItem(new Book("Duplicate Book", "Author", "2023", "B006"), 0, 0);

        // Test return non-existent item
        System.out.println("\nTesting return non-existent item:");
        library.returnItem("INVALID");

        // Test loading file I/O before saving 
        System.out.println("\nTesting file I/O with invalid filename:");
        Library badLoad = Library.loadFromFile("hopefullythisfiledoesnotexistforCS4Atesting.bin");
        // ============ END EDGE CASE TESTS ============
        System.out.println("\n=== SYSTEM WORKS ===");
    }
}
