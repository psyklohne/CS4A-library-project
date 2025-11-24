import java.io.Serializable;

public class Shelf implements Serializable {
    private static final int COMPARTMENTS_PER_SHELF = 15;
    private Item[] compartments;
    
    public Shelf() {
        compartments = new Item[COMPARTMENTS_PER_SHELF];
    }
    
    public Item getCompartment(int compartment) {
        // Trust caller to use valid compartment - fail fast if not
        if (compartment < 0 || compartment >= COMPARTMENTS_PER_SHELF) {
            throw new IndexOutOfBoundsException("Invalid compartment: " + compartment);
        }
        return compartments[compartment];
    }
    
    public void setCompartment(int compartment, Item item) {
        if (compartment < 0 || compartment >= COMPARTMENTS_PER_SHELF) {
            throw new IndexOutOfBoundsException("Invalid compartment: " + compartment);
        }
        compartments[compartment] = item;
    }
    
    public boolean isCompartmentEmpty(int compartment) {
        // Trust the array access - compartment validation happens in getCompartment
        return getCompartment(compartment) == null;
    }
    
    public int getCompartmentCount() {
        return COMPARTMENTS_PER_SHELF;
    }
    
    public void displayContents(int shelfNumber) {
        for (int c = 0; c < COMPARTMENTS_PER_SHELF; c++) {
            Item item = compartments[c];  // Direct access - trust our array bounds
            if (item != null) {
                System.out.println("Shelf " + shelfNumber + ", Compartment " + c + ": " + item);
            }
        }
    }
}
