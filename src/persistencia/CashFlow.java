package persistencia;

import java.util.Date;

public class CashFlow {
    int id;
    String type;
    String concept;
    float amount;
    Date date;
    int category;

    public CashFlow() {
    }

    public CashFlow(int id, String type, String concept, float amount, Date date, int category) {
        this.id = id;
        this.type = type;
        this.concept = concept;
        this.amount = amount;
        this.date = date;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getConcept() {
        return concept;
    }

    public void setConcept(String concept) {
        this.concept = concept;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "CashFlow{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", concept='" + concept + '\'' +
                ", amount=" + amount +
                ", date=" + date +
                ", category=" + category +
                '}';
    }
}
