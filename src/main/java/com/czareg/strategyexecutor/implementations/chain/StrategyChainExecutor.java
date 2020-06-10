package com.czareg.strategyexecutor.implementations.chain;

import com.czareg.strategyexecutor.abstracts.executor.StrategyExecutor;
import com.czareg.strategyexecutor.abstracts.executor.StrategyExecutorException;
import com.czareg.strategyexecutor.abstracts.strategy.Strategy;
import com.czareg.strategyexecutor.abstracts.strategy.StrategyException;

import java.util.LinkedList;
import java.util.List;

public class StrategyChainExecutor<V> implements StrategyExecutor<V> {
    List<Strategy<V>> strategies = new LinkedList<>();

    public void add(Strategy<V> strategy) {
        strategies.add(strategy);
    }

    public V execute() throws StrategyExecutorException {
        for (Strategy<V> strategy : strategies) {
            System.out.println("Checking if strategy " + strategy.getName() + " is valid");
            if (strategy.isValid()) {
                System.out.println("Strategy " + strategy.getName() + " is valid. Trying to get result");
                try {
                    V v = strategy.getResult();
                    System.out.println("Strategy " + strategy.getName() + " finished with success. Returning result.");
                    return v;
                } catch (StrategyException e) {
                    System.out.println("Failed strategy: " + strategy.getName());
                    e.printStackTrace();
                }
            } else {
                System.out.println("Strategy " + strategy.getName() + " is invalid. Skipping");
            }
        }
        throw new StrategyExecutorException("None of the strategies returned result");
    }
}
