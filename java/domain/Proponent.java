package domain;

import java.math.BigDecimal;

public class Proponent {

    private String id;
    private String name;
    private Integer age;
    private BigDecimal monthlyIncome;
    private Boolean isMain;

    public Proponent(String id, String name, Integer age, BigDecimal monthlyIncome, Boolean isMain) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.monthlyIncome = monthlyIncome;
        this.isMain = isMain;
    }

    public String getId() {
        return this.id;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public BigDecimal getMonthlyIncome() {
        return monthlyIncome;
    }

    public Boolean getMain() {
        return isMain;
    }
}
