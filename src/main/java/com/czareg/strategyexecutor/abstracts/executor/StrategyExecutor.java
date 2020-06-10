package com.czareg.strategyexecutor.abstracts.executor;

import com.czareg.strategyexecutor.abstracts.strategy.Strategy;

public interface StrategyExecutor <V> {
    void add(Strategy<V> strategy);
    V execute() throws StrategyExecutorException;
}
