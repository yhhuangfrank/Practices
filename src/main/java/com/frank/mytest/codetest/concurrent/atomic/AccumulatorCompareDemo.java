package com.frank.mytest.codetest.concurrent.atomic;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.concurrent.atomic.LongAdder;

class ClickNumber {
    int number = 0;

    // 方法1 ：synchronized
    public synchronized void clickBySynchronized() {
        number++;
    }

    // 方法2 ：AtomicLong
    AtomicLong atomicLong = new AtomicLong(); // 初始值為 0

    public void clickByAtomicLong() {
        atomicLong.getAndIncrement();
    }

    // 方法 3 :LongAdder
    LongAdder longAdder = new LongAdder();

    public void clickByLongAdder() {
        longAdder.increment();
    }

    // 方法 4 :LongAccumulator
    LongAccumulator longAccumulator = new LongAccumulator(Long::sum, 0);

    public void clickByLongAccumulator() {
        longAccumulator.accumulate(1);
    }

}

// 50 個執行緒，每個加總 1,000,000 次，求總和
public class AccumulatorCompareDemo {

    public static final int MILLION = 1000000;
    public static final int THREAD_NUM = 50;
    public static final String SYNCHRONIZED = "synchronized";
    public static final String ATOMIC_LONG = "atomicLong";
    public static final String LONG_ADDER = "longAdder";
    public static final String LONG_ACCUMULATOR = "longAccumulator";

    public static void main(String[] args) {
        ClickNumber clickNumber = new ClickNumber();

        CountDownLatch countDownLatch1 = new CountDownLatch(THREAD_NUM);
        CountDownLatch countDownLatch2 = new CountDownLatch(THREAD_NUM);
        CountDownLatch countDownLatch3 = new CountDownLatch(THREAD_NUM);
        CountDownLatch countDownLatch4 = new CountDownLatch(THREAD_NUM);

        System.out.println("花費時間為： " + calculateTimeByType(clickNumber, countDownLatch1, SYNCHRONIZED) + " ms, 結果為： " + clickNumber.number);
        System.out.println("花費時間為： " + calculateTimeByType(clickNumber, countDownLatch2, ATOMIC_LONG) + " ms, 結果為： " + clickNumber.atomicLong.get());
        System.out.println("花費時間為： " + calculateTimeByType(clickNumber, countDownLatch3, LONG_ADDER) + " ms, 結果為： " + clickNumber.longAdder.sum());
        System.out.println("花費時間為： " + calculateTimeByType(clickNumber, countDownLatch4, LONG_ACCUMULATOR) + " ms, 結果為： " + clickNumber.longAccumulator.get());
    }

    private static long calculateTimeByType(ClickNumber clickNumber, CountDownLatch countDownLatch, String type) {
        long startTime;
        long endTime;

        startTime = System.currentTimeMillis();
        for (int i = 1; i <= THREAD_NUM; i++) {
            new Thread(() -> {
                try {
                    for (int j = 1; j <= MILLION; j++) {
                        switch (type) {
                            case "synchronized" -> {
                                clickNumber.clickBySynchronized();
                            }
                            case "atomicLong" -> {
                                clickNumber.clickByAtomicLong();
                            }
                            case "longAdder" -> {
                                clickNumber.clickByLongAdder();
                            }
                            case "longAccumulator" -> {
                                clickNumber.clickByLongAccumulator();
                            }
                            default -> {
                                throw new RuntimeException();
                            }
                        }
                    }
                } finally {
                    countDownLatch.countDown();
                }
            }, String.valueOf(i)).start();
        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        endTime = System.currentTimeMillis();
        return endTime - startTime;
    }
}
