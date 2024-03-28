package com.frank.mytest.test.designpattern.responsibilitychain;

public class DepartmentApprover extends Approver{

    public DepartmentApprover(String name) {
        super(name);
    }

    @Override
    public void processRequest(PurchaseRequest purchaseRequest) {
        if (purchaseRequest.getPrice() <= 5000) {
            System.out.println("請求編號 id= " + purchaseRequest.getId() + " 被 " + this.name + " 處理");
            return;
        }
        successor.processRequest(purchaseRequest);
    }
}
