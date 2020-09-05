package electricity.bill.management.system;

public class CusBills {
    private String Month;
    private double Amount;
    private String Status;

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public void setMonth(String Month) {
        this.Month = Month;
    }

    public void setAmount(double Amount) {
        this.Amount = Amount;
    }

    public String getStatus() {
        return Status;
    }

    public String getMonth() {
        return Month;
    }

    public double getAmount() {
        return Amount;
    }

    public CusBills(String Month, double Amount, String Status) {
        this.Month = Month;
        this.Amount = Amount;
        this.Status = Status;
    }

    public CusBills() {
    }
    
}
