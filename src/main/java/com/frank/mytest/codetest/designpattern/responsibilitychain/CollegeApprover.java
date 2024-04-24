package com.frank.mytest.codetest.designpattern.responsibilitychain;

public class CollegeApprover extends Approver{

    public CollegeApprover(String name) {
        super(name);
    }

    @Override
    public void processRequest(PurchaseRequest purchaseRequest) {
        float price = purchaseRequest.getPrice();
        if (price > 5000 && price <= 10000) {
            System.out.println("請求編號 id= " + purchaseRequest.getId() + " 被 " + this.name + " 處理");
            return;
        }
        successor.processRequest(purchaseRequest);
    }
}
