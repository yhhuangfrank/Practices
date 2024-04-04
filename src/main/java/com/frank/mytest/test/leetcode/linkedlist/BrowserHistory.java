package com.frank.mytest.test.leetcode.linkedlist;


public class BrowserHistory {
    public static void main(String[] args) {
        BrowserHistory bh = new BrowserHistory("a");
        bh.visit("b");
        bh.visit("c");
        System.out.println(bh.back(3));
        bh.visit("d");
        System.out.println(bh.forward(1));
    }

    private WebSite curr;
    private WebSite head;

    public BrowserHistory(String homepage) {
        curr = new WebSite(homepage, null, null);
        head = curr;
    }

    public void visit(String url) {
        curr.next = new WebSite(url, curr, null);
        curr = curr.next;
    }

    public String back(int steps) {
        while (steps > 0 && curr != head) {
            steps--;
            curr = curr.prev;
        }
        return curr.url;
    }

    public String forward(int steps) {
        while (steps > 0 && curr.next != null) {
            steps--;
            curr = curr.next;
        }
        return curr.url;
    }

    private static class WebSite {
        String url;
        WebSite prev;
        WebSite next;

        public WebSite(String url, WebSite prev, WebSite next) {
            this.url = url;
            this.prev = prev;
            this.next = next;
        }
    }


}
