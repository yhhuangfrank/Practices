package com.frank.mytest.codetest.designPattern2;

import lombok.Getter;
import lombok.Setter;

public class StatePattern {
    public static void main(String[] args) {
        Document document = new Document();
        System.out.println(document.getState() instanceof Draft); // true

        document.publish();
        System.out.println(document.getState() instanceof Review); // true

        document.publish();
        System.out.println(document.getState() instanceof Draft); // true

        document.setApproved(true);
        document.publish(); // Draft -> Review
        document.publish(); // Review -> Published
        System.out.println(document.getState() instanceof Published); // true

        document.publish();
        System.out.println(document.getState() instanceof Published); // true

    }
}

interface State {
    void handleRequest(Document doc);
}

@Getter
@Setter
class Document {
    private State state;
    private boolean isApproved;

    public Document() {
        this.state = new Draft();
    }

    public void publish() {
        this.state.handleRequest(this);
    }
}

class Draft implements State {
    @Override
    public void handleRequest(Document doc) {
        // Write your code here
        doc.setState(new Review());
    }
}

class Review implements State {
    @Override
    public void handleRequest(Document doc) {
        // Write your code here
        if (doc.isApproved()) {
            doc.setState(new Published());
        } else {
            doc.setState(new Draft());
        }
    }
}

class Published implements State {
    @Override
    public void handleRequest(Document doc) {
        // published doc cannot change state anymore
    }
}
