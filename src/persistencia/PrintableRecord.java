package persistencia;

public class PrintableRecord {
    String companyName;
    float week1;
    float week2;
    float week3;
    float week4;
    float week5;
    float suma;

    public PrintableRecord(String cN, float w1, float w2, float w3, float w4, float w5 ){
        companyName=cN;
        week1=w1;
        week2=w2;
        week3=w3;
        week4=w4;
        week5=w5;
        suma=w1+w2+w3+w4+w5;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public float getWeek1() {
        return week1;
    }

    public void setWeek1(float week1) {
        this.week1 = week1;
    }

    public float getWeek2() {
        return week2;
    }

    public void setWeek2(float week2) {
        this.week2 = week2;
    }

    public float getWeek3() {
        return week3;
    }

    public void setWeek3(float week3) {
        this.week3 = week3;
    }

    public float getWeek4() {
        return week4;
    }

    public void setWeek4(float week4) {
        this.week4 = week4;
    }

    public float getWeek5() {
        return week5;
    }

    public void setWeek5(float week5) {
        this.week5 = week5;
    }

    public float getSuma() {
        return suma;
    }

    public void setSuma(float suma) {
        this.suma = suma;
    }

    @Override
    public String toString() {
        return "PrintableRecord{" +
                "companyName='" + companyName + '\'' +
                ", week1=" + week1 +
                ", week2=" + week2 +
                ", week3=" + week3 +
                ", week4=" + week4 +
                ", week5=" + week5 +
                ", suma=" + suma +
                '}';
    }
}
