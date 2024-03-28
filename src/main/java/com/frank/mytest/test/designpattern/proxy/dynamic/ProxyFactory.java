package com.frank.mytest.test.designpattern.proxy.dynamic;

import lombok.AllArgsConstructor;

import java.lang.reflect.Proxy;

@AllArgsConstructor
public class ProxyFactory {
    // 維護一個目標物件
    private Object target;

    // 給目標物件生成一個代理物件
    public Object getProxyInstance() {
        // ClassLoader: 指定目前目標物件的類讀取器，獲取讀取器的方法固定
        // Class<?>[] interfaces: 目標物件實現的介面類型，使用泛型方法確認類型
        // InvocationHandler: 執行目標物件方法，會觸發事件處理器，把目前執行的目標物件方法做為參數傳入
        return Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                (proxy, method, args) -> {
                    System.out.println("proxy start");
                    return method.invoke(target, args);
                }
        );
    }
}
