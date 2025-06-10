package demo.quickdemo.executors;

import demo.quickdemo.aggregators.PlaceOrderAggregator;
import lombok.SneakyThrows;
import org.stacksaga.ProcessStepManager;
import org.stacksaga.ProcessStepManagerUtil;
import org.stacksaga.RevertHintStore;
import org.stacksaga.annotation.RevertAfter;
import org.stacksaga.annotation.RevertBefore;
import org.stacksaga.annotation.SagaExecutor;
import org.stacksaga.core.SagaExecutionEventName;
import org.stacksaga.exception.execution.NonRetryableExecutorException;
import org.stacksaga.executor.ReactiveCommandExecutor;
import reactor.core.publisher.Mono;

/*
@SagaExecutor(
        executeFor = "order-service",
        value = "OrderInitializeExecutor"
)
public class OrderInitializeExecutor implements CommandExecutor<PlaceOrderAggregator> {

    @SneakyThrows
    @Override
    public ProcessStepManager<PlaceOrderAggregator> doProcess(
            PlaceOrderAggregator currentAggregator,
            ProcessStepManagerUtil<PlaceOrderAggregator> stepManager,
            String idempotencyKey
    ) throws RetryableExecutorException, NonRetryableExecutorException {
        // TODO: execute place order process here
//        Thread.sleep(new Random().nextLong(1000, 3000));
        {
            //updates the aggregator with the order id
            currentAggregator.setOrderId(currentAggregator.getAggregatorTransactionId());
        }
//        return stepManager.complete(() -> "INITIATED_ORDER");
        throw new RuntimeException("fucking error");
//        return stepManager.next(GetUserDetailsExecutor.class, () -> "INITIATED_ORDER");
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
*/
@SagaExecutor(
        executeFor = "order-service",
        value = "OrderInitializeExecutor"
)
public class OrderInitializeExecutor implements ReactiveCommandExecutor<PlaceOrderAggregator> {

    @SneakyThrows
    @Override
    public Mono<ProcessStepManager<PlaceOrderAggregator>> doProcess(
            PlaceOrderAggregator currentAggregator,
            ProcessStepManagerUtil<PlaceOrderAggregator> stepManager,
            String idempotencyKey
    ) {
        // TODO: execute place order process here
//        Thread.sleep(new Random().nextLong(1000, 3000));
        {
            //updates the aggregator with the order id
            currentAggregator.setOrderId(currentAggregator.getAggregatorTransactionId());
        }
//        return stepManager.complete(() -> "INITIATED_ORDER");
//        throw new RuntimeException("fucking error");
//        return Mono.error(RetryableExecutorException.buildWith(new RuntimeException("fucking error")).build());
        return Mono.just(stepManager.next(GetUserDetailsExecutor.class, () -> "INITIATED_ORDER"));
//        return Mono.empty();
    }

    @Override
//    @RevertBefore(startFrom = InitOrderSubBefore1.class)
//    @RevertAfter(startFrom = InitOrderSubAfter1.class)
    public Mono<SagaExecutionEventName> doRevert(NonRetryableExecutorException processException,
                                                 PlaceOrderAggregator finalAggregatorState,
                                                 RevertHintStore revertHintStore,
                                                 String idempotencyKey
    ) {
        revertHintStore.put("ORDER_CANCELLED", "done");

        // TODO: execute place order revert process here
        return Mono.just(() -> "ORDER_CANCELLED");
    }
}
