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
        executeFor = "order-service",
        value = "PlaceOrderInitExecutor"
)
public class PlaceOrderInitExecutor implements CommandExecutor<PlaceOrderAggregator> {

    @SneakyThrows
    @Override
    public ProcessStepManager<PlaceOrderAggregator> doProcess(
            PlaceOrderAggregator currentAggregator,
            ProcessStepManagerUtil<PlaceOrderAggregator> stepManager,
            String idempotencyKey
    ) throws RetryableExecutorException, NonRetryableExecutorException {
        // TODO: execute place order process here
        Thread.sleep(new Random().nextLong(1000, 3000));
        {
            //updates the aggregator with the order id
            currentAggregator.setOrderId(currentAggregator.getAggregatorTransactionId());
        }
        return stepManager.next(GetUserDetailsExecutor.class, () -> "INITIATED_ORDER");
    }

    @Override
    public SagaExecutionEventName doRevert(NonRetryableExecutorException processException,
                                           PlaceOrderAggregator finalAggregatorState,
                                           RevertHintStore revertHintStore,
                                           String idempotencyKey
    ) throws RetryableExecutorException {

        // TODO: execute place order revert process here
        return () -> "ORDER_CANCELLED";
    }
}
