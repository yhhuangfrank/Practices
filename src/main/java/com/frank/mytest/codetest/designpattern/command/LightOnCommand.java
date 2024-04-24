package com.frank.mytest.codetest.designpattern.command;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class LightOnCommand implements Command {
    private LightReceiver receiver;

    @Override
    public void execute() {
        receiver.on();
    }

    @Override
    public void undo() {
        receiver.off();
    }
}
