package com.slb.sharebed.http.bean;

public class OrderFeeDetailEntity {

    /**
     * seconds : 11
     * totalMoney : 0
     * balance : 5000
     */

    private Long seconds;
    private Long totalMoney;
    private Long balance;

    public Long getSeconds() {
        return seconds;
    }

    public void setSeconds(Long seconds) {
        this.seconds = seconds;
    }

    public Long getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(Long totalMoney) {
        this.totalMoney = totalMoney;
    }

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }
}
