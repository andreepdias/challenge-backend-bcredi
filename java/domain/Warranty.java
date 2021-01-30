package domain;

import java.math.BigDecimal;

public class Warranty {

    private String id;
    private BigDecimal value;
    private String province;

    public Warranty(String id, BigDecimal value, String province) {
        this.id = id;
        this.value = value;
        this.province = province;
    }

    public String getId() {
        return id;
    }

    public BigDecimal getValue() {
        return value;
    }

    public String getProvince() {
        return province;
    }

    public boolean isAccepted() {
        return  !(province.equals("PR")
            || province.equals("SC")
            || province.equals("RS"));
    }
}
