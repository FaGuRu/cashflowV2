package persistencia;

import java.util.Date;

public class Record {
    int id;
    String type;
    int week_num;
    java.sql.Date month;
    String company_name;
    float amount;

    public Record() {
    }

    public Record(int id, String type, int week_num, java.sql.Date month, String company_name, float amount) {
        this.id = id;
        this.type = type;
        this.week_num = week_num;
        this.month = month;
        this.company_name = company_name;
        this.amount = amount;
    }

    public Record(String type, int week_num, java.sql.Date month, String company_name, float amount) {
        this.type = type;
        this.week_num = week_num;
        this.month = month;
        this.company_name = company_name;
        this.amount = amount;
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

    public int getWeek_num() {
        return week_num;
    }

    public void setWeek_num(int week_num) {
        this.week_num = week_num;
    }

    public java.sql.Date getMonth() {
        return month;
    }

    public void setMonth(java.sql.Date month) {
        this.month = month;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Record{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", week_num=" + week_num +
                ", month=" + month +
                ", company_name='" + company_name + '\'' +
                ", amount=" + amount +
                '}';
    }
}