package com.frank.mytest.codetest.other;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainTest1 {
    public static void main(String[] args) {

        String json = "{\"nodeDataArray\":[{\"fullPointName\":\"\",\"loc\":\"610 380\",\"img\":\"images/element/toggle.svg\",\"borderColor\":\"rgba(255, 255, 0, 1)\",\"zOrder\":1,\"canWrite\":false,\"stroke\":\"rgba(0, 0, 0, 1)\",\"uid\":1669375793,\"bgColor\":\"rgba(255, 255, 255, 1)\",\"text\":\"A|B\",\"category\":\"toggle\",\"isBroken\":true,\"key\":-3},{\"fullPointName\":\"\",\"loc\":\"910 364.44479293823247\",\"figure\":\"Ellipse\",\"img\":\"images/element/oval.svg\",\"thickness\":2,\"zOrder\":2,\"canWrite\":false,\"fill\":\"rgba(69, 175, 227, 1)\",\"stroke\":\"rgba(0, 0, 0, 1)\",\"uid\":1697097508,\"text\":\"TW\",\"category\":\"shape\",\"dash\":[0,0],\"isBroken\":false,\"key\":-2},{\"fullPointName\":\"AA_BB_eqp1-CC-123@temp2\",\"loc\":\"820 510\",\"figure\":\"FivePointedStar\",\"img\":\"images/element/star.svg\",\"thickness\":2,\"zOrder\":3,\"canWrite\":false,\"fill\":\"rgba(69, 175, 227, 1)\",\"stroke\":\"rgba(0, 0, 0, 1)\",\"uid\":1697101837,\"text\":\"Star\",\"category\":\"shape\",\"dash\":[0,0],\"isBroken\":false,\"key\":-6}],\"linkDataArray\":[],\"class\":\"GraphLinksModel\"}";
//        StringUtils.stripStart(json,"\"");
//        System.out.println(json);

        JSONObject jsonObject = new JSONObject(json);
        JSONArray nodeDataArray = jsonObject.getJSONArray("nodeDataArray");
        for (int i = 0; i < nodeDataArray.length(); i++) {
            JSONObject object = nodeDataArray.getJSONObject(i);
            object.put("index", i);
        }
        String objectString = jsonObject.toString();

        System.out.println(objectString);

//        String s1 = "\"abc,\"def,\n\"ghi\"";
//        String s2 = StringUtils.stripStart(s1, "\"");
//        String s3 = StringUtils.stripEnd(s1, "\"");
//        String s4 = StringEscapeUtils.escapeJava(s1);
//        System.out.println(s1);
//        System.out.println(s2);
//        System.out.println(s3);
//        System.out.println(s4);
    }
}
