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
