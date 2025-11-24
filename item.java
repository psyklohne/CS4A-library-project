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

class Book extends Item {
    private String title;
    private String author;
    private String copyrightDate;
    
    public Book(String title, String author, String copyrightDate, String id) {
        super("Book", "A book titled " + title, id);
        this.title = title;
        this.author = author;
        this.copyrightDate = copyrightDate;
    }
    
    @Override
    public String toString() {
        return "BOOK: " + title + " by " + author + " (" + copyrightDate + ") [ID: " + id + "]";
    }
}

class Movie extends Item {
    private String director;
    private List<String> mainActors;
    
    public Movie(String name, String director, List<String> mainActors, String id) {
        super(name, "A movie", id);
        this.director = director;
        this.mainActors = mainActors;
    }
    
    @Override
    public String toString() {
        return "MOVIE: " + name + " directed by " + director + " [ID: " + id + "]";
    }
}

class Magazine extends Item {
    private String edition;
    private String articleTitle;
    
    public Magazine(String name, String edition, String articleTitle, String id) {
        super(name, "A magazine", id);
        this.edition = edition;
        this.articleTitle = articleTitle;
    }
    
    @Override
    public String toString() {
        return "MAGAZINE: " + name + " - " + edition + " Edition [ID: " + id + "]";
    }
}
