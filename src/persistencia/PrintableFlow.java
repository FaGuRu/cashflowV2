package persistencia;

import com.sun.javafx.scene.web.Printable;

import java.util.List;

public class PrintableFlow {
    String name;
    float week1;
    float week2;
    float week3;
    float week4;
    float week5;
    float total;

    public PrintableFlow(String name, float week1, float week2, float week3, float week4, float week5) {
        this.name = name;
        this.week1 = week1;
        this.week2 = week2;
        this.week3 = week3;
        this.week4 = week4;
        this.week5 = week5;
        this.total = week1+week2+week3+week4+week5;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "PrintableFlow{" +
                "name='" + name + '\'' +
                ", week1=" + week1 +
                ", week2=" + week2 +
                ", week3=" + week3 +
                ", week4=" + week4 +
                ", week5=" + week5 +
                ", total=" + total +
                '}';
    }
    
}
