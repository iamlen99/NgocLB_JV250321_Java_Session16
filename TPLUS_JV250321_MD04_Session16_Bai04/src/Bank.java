public class Bank {
    private int id;
    private double balance;
    public Bank(int id, double balance) {
        this.id = id;
        this.balance = balance;
    }

    public Bank() {
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public double getBalance() {
        return balance;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }
}
