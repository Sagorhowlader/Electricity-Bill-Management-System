
package electricity.bill.management.system;

public class Bills {
    
    private String month;
    private double amount;
    private String status;

    public Bills() {
    }

    public Bills(String month, double amount, String status) {
        this.month = month;
        this.amount = amount;
        this.status = status;
    }

    public double getAmount() {
        return amount;
    }

    public String getMonth() {
        return month;
    }

    public String getStatus() {
        return status;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
}
