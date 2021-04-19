package vo;

import java.util.Date;

public class Transaction {
    private double amount;
    private Long uid;

    private Date transactionTime;
    public Transaction(Long uid,double amount,Date transactionTime){
        this.uid=uid;
        this.amount=amount;
        this.transactionTime=transactionTime;
    }
    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getTransactionTime() {
        return transactionTime;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public void setTransactionTime(Date transactionTime) {
        this.transactionTime = transactionTime;
    }
}
