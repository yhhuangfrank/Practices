package com.frank.mytest.test.designpattern.responsibilitychain;

public class Main {
    public static void main(String[] args) {

        PurchaseRequest request = new PurchaseRequest(1, 31000, 222);

        DepartmentApprover departmentApprover = new DepartmentApprover("黃主任");
        CollegeApprover collegeApprover = new CollegeApprover("楊院長");
        ViceSchoolMasterApprover viceSchoolMasterApprover = new ViceSchoolMasterApprover("王副校長");
        SchoolMasterApprover schoolMasterApprover = new SchoolMasterApprover("林校長");

        // 需要將各個級別串接 (處理人構成環形)
        departmentApprover.setSuccessor(collegeApprover);
        collegeApprover.setSuccessor(viceSchoolMasterApprover);
        viceSchoolMasterApprover.setSuccessor(schoolMasterApprover);
        schoolMasterApprover.setSuccessor(departmentApprover);

        // 處理請求
        departmentApprover.processRequest(request);
    }
}
