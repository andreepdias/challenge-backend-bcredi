package domain;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

public class Proposal {

    private String id;
    private BigDecimal loanValue;
    private Integer numberOfMonthlyInstallments;
    private Map<String, Warranty> warranties;
    private Map<String, Proponent> proponents;

    public Proposal(String id, BigDecimal loanValue, Integer numberOfMonthlyInstallments) {
        this.id = id;
        this.loanValue = loanValue;
        this.numberOfMonthlyInstallments = numberOfMonthlyInstallments;
        this.warranties = new LinkedHashMap<>();
        this.proponents = new LinkedHashMap<>();
    }

    public Proposal() {
    }

    public Map<String, Proponent> getProponents() {
        return proponents;
    }

    public String getId() {
        return id;
    }

    public BigDecimal getLoanValue() {
        return loanValue;
    }

    public Integer getNumberOfMonthlyInstallments() {
        return numberOfMonthlyInstallments;
    }

    public Map<String, Warranty> getWarranties() {
        return this.warranties;
    }
}
