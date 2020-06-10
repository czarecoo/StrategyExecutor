package com.czareg.strategyexecutor.implementations.chain;

import com.czareg.strategyexecutor.abstracts.executor.StrategyExecutor;
import com.czareg.strategyexecutor.abstracts.executor.StrategyExecutorException;
import com.czareg.strategyexecutor.implementations.chain.cim.CimConnectionProvider;
import com.czareg.strategyexecutor.implementations.chain.cim.GetHostnameByCim;
import com.czareg.strategyexecutor.implementations.chain.ipmi.GetHostnameByIpmi;
import org.junit.Test;

public class StrategyChainExecutorTests {
    @Test
    public void test1() {
        StrategyExecutor<String> strategyExecutor = new StrategyChainExecutor<>();
        strategyExecutor.add(new GetHostnameByCim(new CimConnectionProvider()));
        strategyExecutor.add(new GetHostnameByIpmi());

        try {
            String result = strategyExecutor.execute();
            System.out.println(result);
        } catch (StrategyExecutorException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test2() {
        StrategyExecutor<String> strategyExecutor = new StrategyChainExecutor<>();
        strategyExecutor.add(new GetHostnameByIpmi());
        strategyExecutor.add(new GetHostnameByCim(new CimConnectionProvider()));

        try {
            String result = strategyExecutor.execute();
            System.out.println(result);
        } catch (StrategyExecutorException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test3() {
        StrategyExecutor<String> strategyExecutor = new StrategyChainExecutor<>();

        try {
            String result = strategyExecutor.execute();
            System.out.println(result);
        } catch (StrategyExecutorException e) {
            e.printStackTrace();
        }
    }
}