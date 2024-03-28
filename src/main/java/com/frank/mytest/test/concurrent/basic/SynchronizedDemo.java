package com.frank.mytest.test.concurrent.basic;

import java.util.concurrent.TimeUnit;

// 資源類別
class Phone {
    public static synchronized void sendEmail() {
        try {
            TimeUnit.MILLISECONDS.sleep(3000);  // 耗費三秒
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("-------sendEmail");
    }

    public static synchronized void sendText() {
        System.out.println("-------sendText");
    }

    public void hello() {
        System.out.println("------- hello");
    }
}

// 多執行緒鎖的案例 (記得：執行緒 操作 資源類別)
// 鎖的 8 種案例

/**
 * 1. 標準訪問有 ab 兩 thread，先輸出 email 還是 text ?--> 先 email 後 text
 * 2. sendEmail 方法中加入暫停 3 秒鐘，先輸出 email 還是 text ? --> 先 email 後 text
 * 3. 添加一個普通的 hello 方法，先輸出 email 還是 hello ? --> 先 hello 後 email
 * 4. 有兩部手機，先輸出 email 還是 text ? --> 先 text 後 email
 * 5. 有兩個靜態同步方法，先輸出 email 還是 text ? --> 先 email 後 text
 * 6. 有兩個靜態同步方法，有兩部手機，先輸出 email 還是 text ? --> 先 email 後 text
 * 7. 有一個靜態同步方法，有一個普通同步方法，有一部手機，先輸出 email 還是 text ? --> 先 text 後 email
 * 8. 有一個靜態同步方法，有一個普通同步方法，有兩部手機，先輸出 email 還是 text ? --> 先 text 後 email
 *
 * 總結：
 * 1-2
 * ** 一個物件裡如果有多個 synchronized 方法，某一個時刻內，只要一個執行緒去掉用其中一個 synchronized 方法了，那其餘的執行緒只能等待。也就是說，某一個時刻內，
 * 只能有唯一的一個執行緒去訪問 synchronized 方法，鎖的是目前的物件 (this)，被鎖定後，其他的執行緒都不能進入到目前物件的其他 synchronized 方法
 *
 * 3-4
 * ** 加上普通方法後發現和同步鎖無關，換上兩個物件後，不是同一把鎖，因此情況變化
 *
 * 5-6
 * ** 都換成靜態同步方法後
 * 三種 synchronized 鎖的內容有一些差別：
 * 1. 對於普通同步方法，鎖的是目前實例物件，通常指 this，具體一步步手機，所有的普通同步方法用的都是同一把鎖 --> 實例物件本身
 * 2. 對於靜態同步方法，鎖的是目前類的 Class 物件，如 Phone.class 唯一的一個模板
 * 3. 對於同步方法塊，鎖的是 synchronized 括號內的物件
 *
 * 7-8
 * ** 當一個執行緒試圖訪問同步代碼時他首先必須先得到鎖，正常退出或拋出異常時必須釋放鎖
 * 所有的普通同步方法用的都是同一把鎖 (實例物件本身)，就是 new 出來的物件 (this)，也就是說如果一個時力物件的普通同步方法獲得鎖後，
 * 該實例物件的其他普通同步方法必須等待獲取獲取鎖的方法釋放後才能獲得鎖
 *
 * 所有的靜態同步方法用的都是同一把鎖 (類物件本身)，就是唯一模板 Class，實例物件 this 與 Class 兩把鎖的是不同物件，所以靜態同步方法與普通同步方法彼此之間不會有競爭關係，
 * 而一旦一個靜態同步方法獲取鎖後，其他的靜態同步方法都必須等待該方法釋放鎖後才能獲取
 */
public class SynchronizedDemo {

    public static void main(String[] args) {
        Phone phone = new Phone();
        Phone phone2 = new Phone();

        new Thread(() -> {
          phone.sendEmail();
        }, "a").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        new Thread(() -> {
//            phone.sendText();
//            phone.hello();
            phone2.sendText();
        }, "b").start();

    }

}
