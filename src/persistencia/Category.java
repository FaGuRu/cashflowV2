package persistencia;

public class Category {
    int id;
    String name;
    String subcategory;
    String classification;

    public Category() {
    }

    public Category(int id, String name, String subcategory, String classification) {
        this.id = id;
        this.name = name;
        this.subcategory = subcategory;
        this.classification = classification;
    }
    public Category(String name, String subcategory, String classification) {
        this.name = name;
        this.subcategory = subcategory;
        this.classification = classification;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(String subcategory) {
        this.subcategory = subcategory;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    @Override
    public String toString() {
        return name;
    }
}
