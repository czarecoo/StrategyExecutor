package com.czareg.strategyexecutor.implementations.paralel.snmp;

import com.czareg.strategyexecutor.abstracts.strategy.Strategy;
import com.czareg.strategyexecutor.abstracts.strategy.StrategyException;

public class GetSerialBySnmp extends Strategy<String> {
    public GetSerialBySnmp() {
        super("GetSerialBySnmp");
    }

    @Override
    public boolean isValid() {
        return true;
    }

    @Override
    public String getResult() throws StrategyException {
        try {
            Thread.sleep(4000L);
            return "3";
        } catch (InterruptedException e) {
            throw new StrategyException("Sleep interrupted");
        }
    }
}