package com.czareg.strategyexecutor.implementations.chain.cim;

import com.czareg.strategyexecutor.abstracts.strategy.Strategy;
import com.czareg.strategyexecutor.abstracts.strategy.StrategyException;

public class GetHostnameByCim extends Strategy<String> {
    private CimConnectionProvider cimConnectionProvider;
    private CimConnection cimConnection;

    public GetHostnameByCim(CimConnectionProvider cimConnectionProvider) {
        super("GetHostnameByCim");
        this.cimConnectionProvider = cimConnectionProvider;
    }

    public boolean isValid() {
        cimConnection = cimConnectionProvider.forNode();
        return cimConnection != null;
    }

    public String getResult() throws StrategyException {
        throw new StrategyException("Something went wrong");
    }
}
