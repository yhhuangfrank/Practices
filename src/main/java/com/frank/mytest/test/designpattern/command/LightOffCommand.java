package com.frank.mytest.test.designpattern.command;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class LightOffCommand implements Command {
    private LightReceiver receiver;


    @Override
    public void execute() {
        receiver.off();
    }

    @Override
    public void undo() {
        receiver.on();
    }
}
