package com.frank.mytest.test.designpattern.flyweight;

public class Main {
    public static void main(String[] args) {

        WebSiteFactory factory = new WebSiteFactory();

        WebSite webSite1 = factory.getWebSiteCategory("type1");
        webSite1.use(new User("Tom"));

        WebSite webSite2 = factory.getWebSiteCategory("type2");
        webSite2.use(new User("Jack"));

        WebSite webSite3 = factory.getWebSiteCategory("type2");
        System.out.println(webSite3 == webSite2);

        System.out.println("type count of website is : " + factory.getWebSiteCategoryCount());
    }
}
