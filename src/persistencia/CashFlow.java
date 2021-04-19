package persistencia;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Calendar;


public class CashFlow {
    int id;
    String type;
    String concept;
    float amount;
    Date date;
    Category category;
    int week_num;


    public CashFlow(String type, String concept, float amount, Date date, Category category, int week_num) {
        this.type = type;
        this.concept = concept;
        this.amount = amount;
        this.date = date;
        this.category = category;
        this.week_num = week_num;
    }


    public CashFlow() {
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getWeek_num() {
        return week_num;
    }

    public void setWeek_num(int week_num) {
        this.week_num = week_num;
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
                ", weekNumber=" + week_num +
                '}';
    }


}
