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
import org.stacksaga.executor.ReactiveCommandExecutor;
import reactor.core.publisher.Mono;

import java.util.Random;

@SagaExecutor(
        executeFor = "payment-service",
        value = "MakePaymentExecutor"
)
/*
public class MakePaymentExecutor implements CommandExecutor<PlaceOrderAggregator> {

    @SneakyThrows
    @Override
    public ProcessStepManager<PlaceOrderAggregator> doProcess(
            PlaceOrderAggregator currentAggregator,
            ProcessStepManagerUtil<PlaceOrderAggregator> stepManager,
            String idempotencyKey
    ) throws RetryableExecutorException, NonRetryableExecutorException {


        // TODO: execute payment process here
        // access the preAuthRefId from the aggregator to making the request
//        Thread.sleep(new Random().nextLong(1000, 3000));
        {
            //updates the aggregator with the payment id
            currentAggregator.setPaymentId("payment-1");
            currentAggregator.setPaid(true);
        }
//        stepManager.next(StockUpdateExecutor.class, () -> "MADE_PAYMENT");
        return stepManager.next(StockUpdateExecutor.class, () -> "MADE_PAYMENT");
    }

    @Override
    public SagaExecutionEventName doRevert(NonRetryableExecutorException processException,
                                           PlaceOrderAggregator finalAggregatorState,
                                           RevertHintStore revertHintStore,
                                           String idempotencyKey
    ) throws RetryableExecutorException {
        // TODO: execute payment revert process here
        return () -> "PAYMENT_REFUNDED";
    }
}
*/


public class MakePaymentExecutor implements ReactiveCommandExecutor<PlaceOrderAggregator> {

    @SneakyThrows
    @Override
    public Mono<ProcessStepManager<PlaceOrderAggregator>> doProcess(
            PlaceOrderAggregator currentAggregator,
            ProcessStepManagerUtil<PlaceOrderAggregator> stepManager,
            String idempotencyKey
    ) {


        // TODO: execute payment process here
        // access the preAuthRefId from the aggregator to making the request
//        Thread.sleep(new Random().nextLong(1000, 3000));
        {
            //updates the aggregator with the payment id
            currentAggregator.setPaymentId("payment-1");
            currentAggregator.setPaid(true);
        }
//        stepManager.next(StockUpdateExecutor.class, () -> "MADE_PAYMENT");
        return Mono.just(stepManager.next(StockUpdateExecutor.class, () -> "MADE_PAYMENT"));
    }

    @Override
    public Mono<SagaExecutionEventName> doRevert(NonRetryableExecutorException processException,
                                           PlaceOrderAggregator finalAggregatorState,
                                           RevertHintStore revertHintStore,
                                           String idempotencyKey
    )  {
        // TODO: execute payment revert process here
        revertHintStore.put("PAYMENT_REFUNDED", "done");
        return Mono.just((SagaExecutionEventName) () -> "PAYMENT_REFUNDED");
    }
}
