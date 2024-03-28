package com.frank.mytest.test.designpattern.prototype.deepclone;

import lombok.*;

import java.io.*;

// @Data ， 若使用 lombok data 註解會影響拷貝效果，因有預設的 EqualsAndHashCode 行為，效果會變成淺拷貝
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Sheep implements Serializable, Cloneable { // 實現 cloneable 做屬性複製, Serializable 做深拷貝
    private String name;
    private Horse friend;

    public Sheep(String name) {
        this.name = name;
    }

    // 重寫 Object.clone 方法
    @Override
    protected Object clone() {
        Sheep sheep = null;
        try {
            sheep = (Sheep) super.clone();

            // 單獨處理引用類型
            sheep.friend = (Horse) friend.clone();
        } catch (CloneNotSupportedException e) {
            System.out.println(e.getMessage());
        }

        return sheep;
    }

    // 使用物件序列化實現深拷貝
    public Object deepClone() {
        // 創建流
        ByteArrayOutputStream bos = null;
        ObjectOutputStream oos = null;
        ByteArrayInputStream bis = null;
        ObjectInputStream ois = null;

        try {
            // 序列化
            bos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(bos);
            oos.writeObject(this); // 目前的物件以流方式輸出

            // 反序列化
            bis = new ByteArrayInputStream(bos.toByteArray()); // 將 bos 輸出的讀近來
            ois = new ObjectInputStream(bis);

            return ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                ois.close();
                bis.close();
                oos.close();
                bos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
