package com.frank.mytest.test.dsa.hashtable;

import lombok.Getter;
import lombok.ToString;

public class HashtableDemo {

    public static void main(String[] args) {
        HashTab hashTab = new HashTab(7);
        Emp frank = new Emp(1, "Frank");
        Emp jack = new Emp(8, "Jack");
        Emp wendy = new Emp(7, "Wendy");
        hashTab.add(frank);
        hashTab.add(wendy);
        hashTab.add(jack);
        hashTab.list();
        System.out.println(hashTab.findEmpById(8));
        System.out.println(hashTab.findEmpById(14));
    }

    // 創建 hash table 管理多條鏈表
    private static class HashTab {
        private EmpLinkedList[] empLinkedListArray;
        private int size;

        public HashTab(int size) {
            // 初始化 empLinkedListArray
            this.size = size;
            empLinkedListArray = new EmpLinkedList[size];
            // 分別初始化每個鏈表
            for (int i = 0; i < size; i++) {
                empLinkedListArray[i] = new EmpLinkedList();
            }
        }

        // 添加 emp
        public void add(Emp emp) {
            // 根據員工的 id 得到該員工應該添加到哪條鏈表
            int linkedListNo = hashFunction(emp.getId());
            empLinkedListArray[linkedListNo].add(emp);
        }

        public void list() {
            for (int i = 0; i < empLinkedListArray.length; i++) {
                empLinkedListArray[i].traverse(i);
            }
        }

        public Emp findEmpById(int id) {
            int index = hashFunction(id);
            return empLinkedListArray[index].findEmpById(id);
        }

        // 編寫 hash function
        private int hashFunction(int id) {
            // 簡單使用 mod 來運算
            return id % size;
        }
    }

    @ToString
    @Getter
    private static class Emp {
        private int id;
        private String name;
        private Emp next;

        public Emp(int id, String name) {
            this.id = id;
            this.name = name;
        }
    }

    // 使用鏈表儲存 Emp
    private static class EmpLinkedList {
        private Emp head; // 指向第一個員工

        // 添加員工
        public void add(Emp emp) {
            if (head == null) {
                head = emp;
                return;
            }
            // 遍歷到最後一個 emp 後加入
            Emp currentEmp = head;
            while (currentEmp.next != null) {
                currentEmp = currentEmp.next;
            }
            currentEmp.next = emp;
        }

        // 遍歷整個鏈表
        public void traverse(int no) {
            if (head == null) {
                System.out.println("List " + (no + 1) + " is empty");
                return;
            }
            Emp currentEmp = head;
            while (currentEmp != null)  {
                System.out.println("List " + (no + 1) +" 中目前的 emp : " + currentEmp);
                currentEmp = currentEmp.next;
            }
        }

        // 查找
        public Emp findEmpById(int id) {
            if (head == null) return null;

            Emp currentEmp = head;
            while (currentEmp != null) {
                if (currentEmp.getId() == id) {
                    System.out.println("emp found !");
                    return currentEmp;
                }
                currentEmp = currentEmp.next;
            }
            System.out.println("cannot find emp by id : " + id);
            return null;
        }
    }
}
