package com.frank.mytest.service;

import com.google.gson.Gson;
import org.apache.commons.text.StringEscapeUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TextServiceTest {

    @Autowired
    private TextService textService;

    @Test
    public void testInsert() throws JSONException {

        String json = "{\"nodeDataArray\":[{\"fullPointName\":\"\",\"loc\":\"610 380\",\"img\":\"images/element/toggle.svg\",\"borderColor\":\"rgba(255, 255, 0, 1)\",\"zOrder\":1,\"canWrite\":false,\"stroke\":\"rgba(0, 0, 0, 1)\",\"uid\":1669375793,\"bgColor\":\"rgba(255, 255, 255, 1)\",\"text\":\"A|B\",\"category\":\"toggle\",\"isBroken\":true,\"key\":-3},{\"fullPointName\":\"\",\"loc\":\"910 364.44479293823247\",\"figure\":\"Ellipse\",\"img\":\"images/element/oval.svg\",\"thickness\":2,\"zOrder\":2,\"canWrite\":false,\"fill\":\"rgba(69, 175, 227, 1)\",\"stroke\":\"rgba(0, 0, 0, 1)\",\"uid\":1697097508,\"text\":\"TW\",\"category\":\"shape\",\"dash\":[0,0],\"isBroken\":false,\"key\":-2},{\"fullPointName\":\"AA_BB_eqp1-CC-123@temp2\",\"loc\":\"820 510\",\"figure\":\"FivePointedStar\",\"img\":\"images/element/star.svg\",\"thickness\":2,\"zOrder\":3,\"canWrite\":false,\"fill\":\"rgba(69, 175, 227, 1)\",\"stroke\":\"rgba(0, 0, 0, 1)\",\"uid\":1697101837,\"text\":\"Star\",\"category\":\"shape\",\"dash\":[0,0],\"isBroken\":false,\"key\":-6}],\"linkDataArray\":[],\"class\":\"GraphLinksModel\"}";
//        json = StringEscapeUtils.escapeJava(json);
        JSONObject jsonObject = new JSONObject(json);
        JSONArray nodeDataArray = jsonObject.getJSONArray("nodeDataArray");
        for (int i = 0; i < nodeDataArray.length(); i++) {
            JSONObject object = nodeDataArray.getJSONObject(i);
            object.put("index", i);
        }
        String json1 = new Gson().toJson(jsonObject.toString());
//        String newJson = jsonObject.toString();
        System.out.println(json1);
        textService.insertText(json1);
    }

    @Test
    public void findText() {
        System.out.println(textService.findText(1).get());
        System.out.println(textService.findText(2).get());
    }

    @Test
    public void test(){
        Boolean t = null;
//        System.out.println(t != null ? t : true);


        System.out.println(Boolean.logicalXor(t == null, t));

    }

}