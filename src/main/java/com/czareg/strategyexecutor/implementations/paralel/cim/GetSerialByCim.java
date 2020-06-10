package com.czareg.strategyexecutor.implementations.paralel.cim;

import com.czareg.strategyexecutor.abstracts.strategy.Strategy;
import com.czareg.strategyexecutor.abstracts.strategy.StrategyException;

public class GetSerialByCim extends Strategy<String> {
    public GetSerialByCim() {
        super("GetSerialByCim");
    }

    @Override
    public boolean isValid() {
        return false;
    }

    @Override
    public String getResult() throws StrategyException {
        return null;
    }
}
