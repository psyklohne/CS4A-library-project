import java.util.ArrayList;
import java.util.List;
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
