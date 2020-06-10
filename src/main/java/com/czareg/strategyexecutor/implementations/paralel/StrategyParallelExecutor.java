package com.czareg.strategyexecutor.implementations.paralel;

import com.czareg.strategyexecutor.abstracts.executor.StrategyExecutor;
import com.czareg.strategyexecutor.abstracts.executor.StrategyExecutorException;
import com.czareg.strategyexecutor.abstracts.strategy.Strategy;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class StrategyParallelExecutor<V> implements StrategyExecutor<V> {
    List<Strategy<V>> strategies = new LinkedList<Strategy<V>>();
    long timeout;
    TimeUnit timeUnit;

    public StrategyParallelExecutor(long timeout, TimeUnit timeUnit) {
        this.timeout = timeout;
        this.timeUnit = timeUnit;
    }

    public void add(Strategy<V> strategy) {
        strategies.add(strategy);
    }

    public V execute() throws StrategyExecutorException {
        List<Strategy<V>> validStrategies = filterOutInvalidStrategies();
        List<Callable<V>> strategiesWrappedWithCallable = wrapStrategiesWithCallable(validStrategies);

        ExecutorService executorService = createExecutorService(strategiesWrappedWithCallable.size());

        try {
            return executorService.invokeAny(strategiesWrappedWithCallable, timeout, timeUnit);
        } catch (InterruptedException e) {
            throw new StrategyExecutorException("Waiting for strategies interrupted");
        } catch (ExecutionException e) {
            throw new StrategyExecutorException("All strategies failed before given time");
        } catch (TimeoutException e) {
            throw new StrategyExecutorException("None of the strategies returned result in given time");
        } finally {
            closeExecutorService(executorService);
        }
    }

    private List<Strategy<V>> filterOutInvalidStrategies() {
        return strategies.stream()
                .filter(onlyValid())
                .collect(Collectors.toList());
    }

    private Predicate<Strategy<V>> onlyValid() {
        return strategy -> {
            System.out.println("Checking if strategy " + strategy.getName() + " is valid");
            if (strategy.isValid()) {
                System.out.println("Strategy " + strategy.getName() + " is valid. Wrapping in callable");
                return true;
            } else {
                System.out.println("Strategy " + strategy.getName() + " is invalid. Skipping");
                return false;
            }
        };
    }

    private List<Callable<V>> wrapStrategiesWithCallable(List<Strategy<V>> validStrategies) {
        return validStrategies.stream()
                .map(StrategyCallable::new)
                .collect(Collectors.toList());
    }

    private ExecutorService createExecutorService(int threads) {
        System.out.println("Created executor service with " + threads + " threads");
        return Executors.newFixedThreadPool(threads);
    }

    private void closeExecutorService(ExecutorService executorService) {
        System.out.println("Closing executor service");
        executorService.shutdownNow();
    }
}
