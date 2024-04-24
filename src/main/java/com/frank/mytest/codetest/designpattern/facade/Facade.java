package com.frank.mytest.codetest.designpattern.facade;

//

/**
 * 包裝子系統的外觀類，為調用端提供統一的調用介面，
 * 外觀類知道哪些子系統適合處理請求，從而轉給適當子系統物件
 */
public class Facade {

    private DVDPlayer dvdPlayer;
    private PopcornMachine popcornMachine;

    public Facade() {
        this.dvdPlayer = DVDPlayer.getInstance();
        this.popcornMachine = PopcornMachine.getInstance();
    }

    public void ready() {
        dvdPlayer.on();
        popcornMachine.on();
    }

    public void off() {
        popcornMachine.off();
        dvdPlayer.off();
    }
}
