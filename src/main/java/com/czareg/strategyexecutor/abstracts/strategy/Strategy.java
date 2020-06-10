package com.czareg.strategyexecutor.abstracts.strategy;

public abstract class Strategy<V> {
    String name;

    public Strategy(String name) {
        this.name = name;
    }

    public abstract boolean isValid();

    public abstract V getResult() throws StrategyException;

    public String getName() {
        return name;
    }
}
