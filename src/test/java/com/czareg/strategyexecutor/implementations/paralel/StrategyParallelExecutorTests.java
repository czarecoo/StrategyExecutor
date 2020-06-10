package com.czareg.strategyexecutor.implementations.paralel;

import com.czareg.strategyexecutor.abstracts.executor.StrategyExecutor;
import com.czareg.strategyexecutor.abstracts.executor.StrategyExecutorException;
import com.czareg.strategyexecutor.implementations.chain.ipmi.GetHostnameByIpmi;
import com.czareg.strategyexecutor.implementations.paralel.cim.GetSerialByCim;
import com.czareg.strategyexecutor.implementations.paralel.ipmi.GetSerialByIpmi;
import com.czareg.strategyexecutor.implementations.paralel.redfish.GetSerialByRedfish;
import com.czareg.strategyexecutor.implementations.paralel.snmp.GetSerialBySnmp;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

public class StrategyParallelExecutorTests {
    @Test
    public void test1() {
        StrategyExecutor<String> strategyExecutor = new StrategyParallelExecutor<>(5, TimeUnit.SECONDS);
        strategyExecutor.add(new GetSerialByCim());
        strategyExecutor.add(new GetSerialByIpmi());
        strategyExecutor.add(new GetSerialByRedfish());
        strategyExecutor.add(new GetSerialBySnmp());

        try {
            String result = strategyExecutor.execute();
            System.out.println(result);
        } catch (StrategyExecutorException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test2() {
        StrategyExecutor<String> strategyExecutor = new StrategyParallelExecutor<>(5, TimeUnit.SECONDS);
        strategyExecutor.add(new GetHostnameByIpmi());
        try {
            String result = strategyExecutor.execute();
            System.out.println(result);
        } catch (StrategyExecutorException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test3() {
        StrategyExecutor<String> strategyExecutor = new StrategyParallelExecutor<>(1, TimeUnit.SECONDS);
        strategyExecutor.add(new GetSerialBySnmp());
        try {
            String result = strategyExecutor.execute();
            System.out.println(result);
        } catch (StrategyExecutorException e) {
            e.printStackTrace();
        }
    }
}