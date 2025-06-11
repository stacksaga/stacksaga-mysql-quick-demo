package demo.quickdemo.executors;

import demo.quickdemo.aggregators.PlaceOrderAggregator;
import lombok.SneakyThrows;
import org.stacksaga.ProcessStepManager;
import org.stacksaga.ProcessStepManagerUtil;
import org.stacksaga.RevertHintStore;
import org.stacksaga.annotation.SagaExecutor;
import org.stacksaga.core.SagaExecutionEventName;
import org.stacksaga.exception.RetryableExecutorException;
import org.stacksaga.exception.execution.NonRetryableExecutorException;
import org.stacksaga.executor.CommandExecutor;

import java.util.Random;

@SagaExecutor(
        executeFor = "stock-service",
        value = "StockUpdateExecutor"
)
public class StockUpdateExecutor implements CommandExecutor<PlaceOrderAggregator> {

    @SneakyThrows
    @Override
    public ProcessStepManager<PlaceOrderAggregator> doProcess(
            PlaceOrderAggregator currentAggregator,
            ProcessStepManagerUtil<PlaceOrderAggregator> stepManager,
            String idempotencyKey
    ) throws RetryableExecutorException, NonRetryableExecutorException {

        // TODO: execute stock update process here
//        Thread.sleep(new Random().nextLong(1000, 3000));
        throw new RuntimeException("STOCK_UPDATED");
//
//        return stepManager.complete(() -> "STOCK_UPDATED");
    }

    @Override
    public SagaExecutionEventName doRevert(NonRetryableExecutorException processException,
                                           PlaceOrderAggregator finalAggregatorState,
                                           RevertHintStore revertHintStore,
                                           String idempotencyKey
    ) throws RetryableExecutorException {

        throw new RuntimeException("STOCK_UPDATED");
        // TODO: execute stock revert process here
//        return () -> "STOCK_REVERTED";
    }
}
