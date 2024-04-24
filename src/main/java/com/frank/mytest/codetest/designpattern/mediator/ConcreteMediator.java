package com.frank.mytest.codetest.designpattern.mediator;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

// 具體的中介者物件
@AllArgsConstructor
@NoArgsConstructor
public class ConcreteMediator extends Mediator{
    private Map<String, Colleague> colleagueMap = new HashMap<>();
    private Map<String, String> interMap = new HashMap<>();

    @Override
    public void register(String colleagueName, Colleague colleague) {
        colleagueMap.put(colleagueName, colleague);

        if (colleague instanceof Alarm) {
            interMap.put("Alarm", colleagueName);
        } else if (colleague instanceof CoffeeMachine) {
            interMap.put("CoffeeMachine", colleagueName);
        } else if (colleague instanceof TV) {
            interMap.put("TV", colleagueName);
        } else if (colleague instanceof Curtain) {
            interMap.put("Curtain", colleagueName);
        }
    }

    // 核心方法。根據得到資訊完成對應任務，協調各 colleague
    @Override
    public void getMessage(int changeState, String name) {
        if (colleagueMap.get(name) instanceof Alarm) {
            if (changeState == 0) {
                CoffeeMachine coffeeMachine = (CoffeeMachine) colleagueMap.get(interMap.get("CoffeeMachine"));
                TV tv = (TV) colleagueMap.get(interMap.get("TV"));
                coffeeMachine.start();
                tv.start();
            } else if (changeState == 1) {
                TV tv = (TV) colleagueMap.get(interMap.get("TV"));
                tv.stop();
            }
        } else if (colleagueMap.get(name) instanceof CoffeeMachine) {
            CoffeeMachine coffeeMachine = (CoffeeMachine) colleagueMap.get(interMap.get("CoffeeMachine"));
            coffeeMachine.start();
        }
        // 其餘機器依此類推
    }

    @Override
    public void sendMessage() {

    }
}
