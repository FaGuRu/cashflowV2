package Persistencia;

public class Category {
    String name;
    String subcategory;
    String classification;

    public Category() {
    }

    public Category(String name, String subcategory, String classification) {
        this.name = name;
        this.subcategory = subcategory;
        this.classification = classification;
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
        return "Category{" +
                "name='" + name + '\'' +
                ", subcategory='" + subcategory + '\'' +
                ", classification='" + classification + '\'' +
                '}';
    }
}
