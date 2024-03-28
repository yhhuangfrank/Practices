package com.frank.mytest.test.concurrent.atomic;

import lombok.*;

import java.util.concurrent.atomic.AtomicStampedReference;

@Data
@AllArgsConstructor
@NoArgsConstructor
class Book {
    private int id;
    private String bookName;
}

public class AtomicStampDemo {

    public static void main(String[] args) {

        Book java = new Book(1, "java");
        Book mysql = new Book(2, "mysql");

        // 初始引用為 java book，版本為 1
        AtomicStampedReference stampedReference = new AtomicStampedReference(java, 1);

        System.out.println(stampedReference.getReference() + "\t" + stampedReference.getStamp());

        boolean b;

        b = stampedReference.compareAndSet(java, mysql, stampedReference.getStamp(), stampedReference.getStamp() + 1);
        System.out.println(b + "\t" + stampedReference.getReference() + "\t" + stampedReference.getStamp());
        b = stampedReference.compareAndSet(mysql, java, stampedReference.getStamp(), stampedReference.getStamp() + 1);
        System.out.println(b + "\t" + stampedReference.getReference() + "\t" + stampedReference.getStamp());

    }
}
