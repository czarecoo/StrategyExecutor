package com.czareg.strategyexecutor.implementations.chain.cim;

public class CimConnectionProvider {
    public CimConnection forNode(){
        return new CimConnection();
    }
}
