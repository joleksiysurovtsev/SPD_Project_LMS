package com.lms.spd.litfactory;

public class LInternetArticleFactory implements LitFactory {
    @Override
    public LitBuilder createLitBuilder() {
        return new LitInternetArticleBuilder();
    }
}
