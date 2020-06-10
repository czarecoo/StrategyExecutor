package com.czareg.strategyexecutor.implementations.paralel.ipmi;

import com.czareg.strategyexecutor.abstracts.strategy.Strategy;
import com.czareg.strategyexecutor.abstracts.strategy.StrategyException;

public class GetSerialByIpmi extends Strategy<String> {
    public GetSerialByIpmi() {
        super("GetSerialByIpmi");
    }

    @Override
    public boolean isValid() {
        return true;
    }

    @Override
    public String getResult() throws StrategyException {
        try {
            Thread.sleep(2000L);
            return "1";
        } catch (InterruptedException e) {
            throw new StrategyException("Sleep interrupted");
        }
    }
}
