package transaction;

public class Flow {
    int amount;
    String description;

    public int getAmount() {
        return amount;
    }

    public Flow(int amount,String description){
        this.amount=amount;
        this.description=description;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
