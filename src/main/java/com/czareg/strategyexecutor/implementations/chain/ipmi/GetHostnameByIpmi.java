package com.czareg.strategyexecutor.implementations.chain.ipmi;

import com.czareg.strategyexecutor.abstracts.strategy.Strategy;
import com.czareg.strategyexecutor.abstracts.strategy.StrategyException;

public class GetHostnameByIpmi extends Strategy<String> {
    public GetHostnameByIpmi() {
        super("GetHostnameNameByIpmi");
    }

    public boolean isValid() {
        return true;
    }

    public String getResult() throws StrategyException {
        return "2";
    }
}
