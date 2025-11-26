import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Shelf implements Serializable {
    private static final int COMPARTMENTS_PER_SHELF = 15;
    private Item[] compartments; // Array with one compartment holding one item
    
    public Shelf() {
        compartments = new Item[COMPARTMENTS_PER_SHELF];
    }
    
    public Item getCompartment(int compartment) {
        // Compartment validity check
        if (compartment < 0 || compartment >= COMPARTMENTS_PER_SHELF) {
            throw new IndexOutOfBoundsException("Invalid compartment: " + compartment);
        }
        return compartments[compartment];
    }
    
    public void setCompartment(int compartment, Item item) {
         // Compartment validity check
        if (compartment < 0 || compartment >= COMPARTMENTS_PER_SHELF) {
            throw new IndexOutOfBoundsException("Invalid compartment: " + compartment);
        }
        compartments[compartment] = item;
    }
    
    public boolean isCompartmentEmpty(int compartment) {
        // Compartment validation happens in getCompartment
        return getCompartment(compartment) == null;
    }
    
    public int getCompartmentCount() {
        return COMPARTMENTS_PER_SHELF;
    }
    
    public List<String> getCompartmentContents() {
        List<String> contents = new ArrayList<>();
        for (int c = 0; c < COMPARTMENTS_PER_SHELF; c++) {
            if (compartments[c] != null) {
                contents.add("Compartment " + c + ": " + compartments[c]);
            }
        }
        return contents;
    }
}
