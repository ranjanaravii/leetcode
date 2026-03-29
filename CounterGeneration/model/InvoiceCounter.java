package CounterGeneration.model;


public class InvoiceCounter {
    private Long id;
    private String financialYear;
    private int version;

    public void setId(Long id) {
        this.id = id;
    }

    public void setFinancialYear(String financialYear) {
        this.financialYear = financialYear;
    }

    public void setCounter(Long counter) {
        this.counter = counter;
    }

    private Long counter;

    public Long getId() {
        return id;
    }

    public String getFinancialYear() {
        return financialYear;
    }

    public Long getCounter() {
        return counter;
    }
}
