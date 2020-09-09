package com.twu;

import java.util.ArrayList;

public class Event {
    private String eventName;
    private int vote = 0;
    private Boolean isSuper = false;
    private Boolean purchase = false;
    private int purchaseRange ;
    private int purchaseMoney = 0;

    public Boolean getPurchase() {
        return purchase;
    }

    public void setPurchase(Boolean purchase) {
        this.purchase = purchase;
    }

    public int getPurchaseRange() {
        return purchaseRange;
    }

    public void setPurchaseRange(int purchaseRange) {
        this.purchaseRange = purchaseRange;
    }

    public int getPurchaseMoney() {
        return purchaseMoney;
    }

    public void setPurchaseMoney(int purchaseMoney) {
        this.purchaseMoney = purchaseMoney;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventName() {
        return eventName;
    }

    public int getVote() {
        return vote;
    }

    public Boolean getSuper() {
        return isSuper;
    }

    public void setVote(int vote) {
        this.vote = vote;
    }

    public void setSuper(Boolean aSuper) {
        isSuper = aSuper;
    }

}
