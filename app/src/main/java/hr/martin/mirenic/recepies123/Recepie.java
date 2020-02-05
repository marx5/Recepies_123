package hr.martin.mirenic.recepies123;

public class Recepie {
    private String id ;
    private String title;
    private String keyWords;
    private String createdBy;
    private String dificulty;
    private String creationDate;
    private String text;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getKeyWords() {
        return keyWords;
    }

    public void setKeyWords(String keyWords) {
        this.keyWords = keyWords;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getDificulty() {
        return dificulty;
    }

    public void setDificulty(String dificulty) {
        this.dificulty = dificulty;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Recepie(String id, String title, String keyWords, String createdBy, String dificulty, String creationDate, String text) {
        this.id = id;
        this.title = title;
        this.keyWords = keyWords;
        this.createdBy = createdBy;
        this.dificulty = dificulty;
        this.creationDate = creationDate;
        this.text = text;
    }

    @Override
    public String toString() {
        return "Recepie{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", keyWords='" + keyWords + '\'' +
                ", createdBy='" + createdBy + '\'' +
                ", dificulty='" + dificulty + '\'' +
                ", creationDate='" + creationDate + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
