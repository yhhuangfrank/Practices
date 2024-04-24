package com.frank.mytest.codetest.dsa.algo.huffmantcode;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.*;
import java.util.*;

public class HuffmanCode {
    private static Map<Byte, String> huffmanCodeMap; // 於 huffmanZip 時初始化

    public static void main(String[] args) {
        String content = "i like like like java do you like a java";
        byte[] contentBytes = content.getBytes();
        System.out.println(contentBytes.length); // 40


//        createHuffmanCodeMap(createHuffmanTree(createNodes(contentBytes))).forEach((k, v) -> System.out.println(k + " ," + v));
//        Map<Byte, String> huffmanCodeMap = createHuffmanCodeMap(createHuffmanTree(createNodes(contentBytes)));
//        byte[] zippedBytes = huffmanZip(contentBytes);
//        byte[] decodedBytes = decode(huffmanCodeMap, zippedBytes);
//        System.out.println(Arrays.toString(decodedBytes));
//        System.out.println(new String(decodedBytes));

//        byte[] zippedString = huffmanZip(contentBytes);
//        System.out.println(Arrays.toString(zippedString) + " length: " + zippedString.length); // 長度為 17 -> 壓縮了 byte 長度
//
//        // 測試
//        System.out.println(byteToString(false, (byte) -88));
//        System.out.println(byteToString(true, (byte) 1));

        // 測試壓縮文件
//        String srcFile = "/Users/frank/Downloads/aaa.txt";
//        String destFile = "/Users/frank/Downloads/aaa.zip";
//        String destFile2 = "/Users/frank/Downloads/aaa2.txt";
//        zipFile(srcFile, destFile);
//        unZipFile(destFile, destFile2);
    }


    // 透過 nodes 構建 Huffman tree
    private static List<Node> createNodes(byte[] bytes) {
        List<Node> nodes = new ArrayList<>();
        Map<Byte, Integer> counts = new HashMap<>();

        for (byte aByte : bytes) {
            // 若 key 對應值 value 為 null, 填入預設 value，若不為 null，依照第三個參數函數生成的新值進行改寫
            // 此處產生新值方法為 舊有的值 + 1 (累加)
            counts.merge(aByte, 1, Integer::sum);
        }

        counts.forEach((k, v) -> nodes.add(new Node(k, v)));
        return nodes;
    }

    private static Node createHuffmanTree(List<Node> nodes) {
        while (nodes.size() > 1) {
            // 排序
            nodes.sort(Comparator.comparingInt(Node::getWeight));
            // 取最小的兩個節點
            Node leftNode = nodes.get(0);
            Node rightNode = nodes.get(1);
            // 生成新父節點
            int parentWeight = leftNode.getWeight() + rightNode.getWeight();
            Node parent = new Node(null, parentWeight); // parent 沒有 data
            parent.setLeft(leftNode);
            parent.setRight(rightNode);
            // 移除處理過的節點與把父節點加入
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            nodes.add(parent);
        }

        return nodes.get(0); // 返回根節點
    }

    // 從 Huffman tree 生成字符對應的 Huffman 編碼
    // 如 32 -> 01, 97 -> 100 ...(向左為 0, 向右為 1) 紀錄路徑
    private static Map<Byte, String> createHuffmanCodeMap(Node rootNode) {
        Map<Byte, String> huffmanCodeMap = new HashMap<>();
        if (rootNode == null) return huffmanCodeMap;
        String initialCode = "";
        // 處理 root 左子樹
        getHuffmanCode(rootNode.getLeft(), "0", initialCode, huffmanCodeMap);
        // 處理 root 右子樹
        getHuffmanCode(rootNode.getRight(), "1", initialCode, huffmanCodeMap);
        return huffmanCodeMap;
    }

    private static void getHuffmanCode(Node node, String code, String currentCode, Map<Byte, String> huffmanCodeMap) {
        // 使用目前累計的 code 建立 StringBuilder
        StringBuilder stringBuilder = new StringBuilder(currentCode);
        stringBuilder.append(code); // 拼接目前的 code
        if (node == null) return;

        // 判斷目前節點是否葉子節點
        if (node.getData() == null) { // 非葉子節點
            // 向左遞迴
            getHuffmanCode(node.getLeft(), "0", stringBuilder.toString(), huffmanCodeMap);
            // 向右遞迴
            getHuffmanCode(node.getRight(), "1", stringBuilder.toString(), huffmanCodeMap);
        } else {
            // 到葉子節點，存入每個字符對應的編碼到 map
            huffmanCodeMap.put(node.getData(), stringBuilder.toString());
        }
    }

    public static byte[] huffmanZip(byte[] bytes) {
        // 根據原始字串的 byte[] 建立 huffman tree 葉子節點
        List<Node> nodes = createNodes(bytes);
        // 根據葉子節點構建 huffman tree
        Node rootNode = createHuffmanTree(nodes);
        // 取得 Huffman 編碼表
        huffmanCodeMap = createHuffmanCodeMap(rootNode);

        return zipString(huffmanCodeMap, bytes);
    }

    // 將一個字串對應的 byte[] ，透過升成的 Huffman 編碼表，返回由 huffman 編碼組成的新的壓縮後 byte[]
    private static byte[] zipString(Map<Byte, String> huffmanCodeMap, byte[] bytes) {
        // 將原始字串 byte array 使用 huffman code 重新生成新的二進位字串
        StringBuilder stringBuilder = new StringBuilder();
        for (byte aByte : bytes) {
            stringBuilder.append(huffmanCodeMap.get(aByte));
        }

        // 按照每 8 位元生成新的 byte[]
        // 1) 統計返回 byte[] 的長度
        int len;
        if (stringBuilder.length() % 8 == 0) {
            len = stringBuilder.length() / 8;
        } else {
            len = stringBuilder.length() / 8 + 1;
        }
        // 2) 創建儲存壓縮後的 byte[]
        byte[] newBytes = new byte[len];
        int index = 0; // 紀錄第幾個 byte
        for (int i = 0; i < stringBuilder.length(); i += 8) { // 每 8 位一組
            String byteString;
            if (i + 8 > stringBuilder.length()) {// 如果不足 8 位
                byteString = stringBuilder.substring(i);
            } else {
                byteString = stringBuilder.substring(i, i + 8);
            }
            newBytes[index] = (byte) Integer.parseInt(byteString, 2); // 由 2 進制數轉為 10 進制數
            index++;
        }
        return newBytes;
    }

    // 對一個文件進行壓縮
    public static void zipFile(String srcFile, String destFile) {
        try (FileInputStream fis = new FileInputStream(srcFile);
             FileOutputStream fos = new FileOutputStream(destFile);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {

            byte[] bytes = new byte[fis.available()];
            fis.read(bytes);

            // 獲取到文件對應的 huffman 壓縮後的 bytes
            byte[] huffmanZipBytes = huffmanZip(bytes);
            // 把壓縮 array 寫入壓縮文件
            oos.writeObject(huffmanZipBytes);
            // 使用物件輸出流輸出壓縮文件，為了以後恢復原文件時使用
            // 注意一定要把 Huffman code 寫入壓縮文件
            oos.writeObject(huffmanCodeMap);

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("壓縮完成！");
    }

    /**
     * 解壓文件
     *
     * @param zipFile  壓縮文件
     * @param destFile 解壓後的文件
     */
    public static void unZipFile(String zipFile, String destFile) {
        try (FileInputStream fis = new FileInputStream(zipFile);
             ObjectInputStream ois = new ObjectInputStream(fis);
             FileOutputStream fos = new FileOutputStream(destFile)) {

            // 讀取 byte[] huffmanBytes
            byte[] huffmanBytes = (byte[]) ois.readObject();

            // 讀取 HuffmanCodeMap
            Map<Byte, String> huffmanCodeMap = (Map<Byte, String>) ois.readObject();

            byte[] decoded = decode(huffmanCodeMap, huffmanBytes);
            fos.write(decoded);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("解壓完成！");
    }

    // 對壓縮數據的解碼，返回原有字串 byte[]
    private static byte[] decode(Map<Byte, String> huffmanCodeMap, byte[] huffmanBytes) {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < huffmanBytes.length; i++) {
            String byteString;
            // 如果是最後一個 byte，不需補高位到字串中
            boolean needEightDigit = (i != (huffmanBytes.length - 1));
            byteString = byteToString(needEightDigit, huffmanBytes[i]);
            stringBuilder.append(byteString);
        }

        // 把字串按照指定的 huffman code 進行解碼
        // 1) 將 huffmanCodeMap key, value 反轉，以便從 huffman code 查詢到對應的 byte
        Map<String, Byte> reverseHuffmanCodeMap = new HashMap<>();
        huffmanCodeMap.forEach((k, v) -> reverseHuffmanCodeMap.put(v, k));
        // 2) 將整個 stringBuilder 結合 reverseHuffmanCodeMap  解析成原始字串的 byte[]
        return reverseUnZippedString(reverseHuffmanCodeMap, stringBuilder.toString());
    }

    // 掃描整個 unzippedString，若有對應的 huffmanCode ，轉譯為字符 byte 後儲存到 list
    private static byte[] reverseUnZippedString(Map<String, Byte> reverseHuffmanCodeMap, String unzippedString) {
        List<Byte> bytes = new ArrayList<>(); // 儲存原始字串的 byte

        int scanPointer = 0;
        while (scanPointer < unzippedString.length()) {
            boolean needCheckHuffmanCode = true;
            int tempPointer = 1;
            Byte b = null;
            // tempPointer 不斷往前比對，當有比對到 map 對應值時停止
            while (needCheckHuffmanCode) {
                String huffmanCode = unzippedString.substring(scanPointer, scanPointer + tempPointer);
                b = reverseHuffmanCodeMap.get(huffmanCode);
                if (b != null) {
                    needCheckHuffmanCode = false;
                } else {
                    tempPointer++; // tempPointer 往前
                }
            }
            bytes.add(b);
            scanPointer += tempPointer; // 直接移動到 tempPointer 指向位置，再往後比對
        }

        // 將 byte list 轉為 byte[]
        byte[] originalBytes = new byte[bytes.size()];
        int index = 0;
        for (Byte aByte : bytes) {
            originalBytes[index++] = aByte;
        }
        return originalBytes;
    }

    // 將一個 byte 轉為一個二進制的字串
    private static String byteToString(boolean needEightDigit, byte b) {
        int temp = b; // 轉為 int
        // 如果是正數，Integer.toBinaryString 不會有 8 個位，需補位
        // 如果是負數，補位後擷取的字串依舊不變，因此可統一用一個 boolean 處理
        if (needEightDigit) {
            // 256: 1 0000 0000
            // 跟 256 做位元 "或" 運算，補足 8 個位數
            // ex: 1 |= 256 -> 1 0000 0001
            temp |= 256;
        }
        String byteString = Integer.toBinaryString(temp);

        // byte[] 最後一個數可能原始位元不滿 8 位元，只需輸出 Integer.toBinaryString 解析的字串
        // 若最後的數小於 0 ，則已滿 8 位元，須擷取最後 8 位
        if (needEightDigit || b < 0) {
            return byteString.substring(byteString.length() - 8);
        } else {
            return byteString;
        }
    }
}

// 樹的節點，包含字母資訊與出現次數 (Huffman tree 的權重)
@Getter
@Setter
@ToString
class Node {
    private Byte data; // 存放字符，比如 'a'=97

    private int weight; // 權重

    @ToString.Exclude
    private Node left;

    @ToString.Exclude
    private Node right;

    public Node(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }
}
