package com.czareg.strategyexecutor.implementations.paralel;

import com.czareg.strategyexecutor.abstracts.strategy.Strategy;
import com.czareg.strategyexecutor.abstracts.strategy.StrategyException;

import java.util.concurrent.Callable;

class StrategyCallable<V> implements Callable<V> {
    Strategy<V> strategy;

    StrategyCallable(Strategy<V> strategy) {
        this.strategy = strategy;
    }

    @Override
    public V call() throws StrategyException {
        V result = strategy.getResult();
        System.out.println("Strategy " + strategy.getName() + " finished with success. Returning result.");
        return result;
    }
}
