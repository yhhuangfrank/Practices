package com.frank.mytest.codetest.designpattern.responsibilitychain;

public class ViceSchoolMasterApprover extends Approver{

    public ViceSchoolMasterApprover(String name) {
        super(name);
    }
    @Override
    public void processRequest(PurchaseRequest purchaseRequest) {
        float price = purchaseRequest.getPrice();
        if (price > 10000 && price <= 30000) {
            System.out.println("請求編號 id= " + purchaseRequest.getId() + " 被 " + this.name + " 處理");
            return;
        }
        successor.processRequest(purchaseRequest);
    }
}
