package com.lms.spd.litfactory;

public class LBookFactory implements LitFactory {
    @Override
    public LitBuilder createLitBuilder() {
        return new LitBookBuilder();
    }
}
