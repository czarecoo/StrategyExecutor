package com.czareg.strategyexecutor.implementations.paralel.redfish;

import com.czareg.strategyexecutor.abstracts.strategy.Strategy;
import com.czareg.strategyexecutor.abstracts.strategy.StrategyException;

public class GetSerialByRedfish extends Strategy<String> {
    public GetSerialByRedfish() {
        super("GetSerialByRedfish");
    }

    @Override
    public boolean isValid() {
        return true;
    }

    @Override
    public String getResult() throws StrategyException {
        try {
            Thread.sleep(3000L);
            return "2";
        } catch (InterruptedException e) {
            throw new StrategyException("Sleep interrupted");
        }
    }
}