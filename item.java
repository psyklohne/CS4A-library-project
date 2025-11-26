import java.io.Serializable;
import java.util.List;

abstract class Item implements Serializable {
    protected String name;
    protected String description;
    protected String id;
    
    public Item(String name, String description, String id) {
        this.name = name;
        this.description = description;
        this.id = id;
    }
    
    public String getName() { return name; }
    public String getDescription() { return description; }
    public String getId() { return id; }
    
    @Override
    public abstract String toString();
}
