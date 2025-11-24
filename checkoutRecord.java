import java.io.Serializable;

class CheckoutRecord implements Serializable {
    private Item item;           // Confident this will never be null
    private String borrower;
    private int dueDays;
    private int shelf;
    private int compartment;
    
    public CheckoutRecord(Item item, String borrower, int dueDays, 
                         int shelf, int compartment) {
        // Trust that checkoutItem validates item before calling us
        this.item = item;
        this.borrower = borrower;
        this.dueDays = dueDays;
        this.shelf = shelf;
        this.compartment = compartment;
    }
    
    public Item getItem() { return item; }
    public String getBorrower() { return borrower; }
    public int getDueDays() { return dueDays; }
    public int getShelf() { return shelf; }
    public int getCompartment() { return compartment; }
    
    public String getItemId() { return item.getId(); }
}
