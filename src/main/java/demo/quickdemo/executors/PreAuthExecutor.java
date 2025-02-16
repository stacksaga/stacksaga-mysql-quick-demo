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

@SagaExecutor(executeFor = "payment-service", value = "PreAuthExecutor")
public class PreAuthExecutor implements CommandExecutor<PlaceOrderAggregator> {
    @SneakyThrows
    @Override
    public ProcessStepManager<PlaceOrderAggregator> doProcess(PlaceOrderAggregator currentAggregator, ProcessStepManagerUtil<PlaceOrderAggregator> stepManager, String idempotencyKey) throws RetryableExecutorException, NonRetryableExecutorException {
        // TODO: make the pre-auth from payment-service
        Thread.sleep(new Random().nextLong(1000, 3000));
        {
            //updates the pre-auth reference ID
            currentAggregator.setPreAuthRefId("822343439413136");
        }
        return stepManager.next(MakePaymentExecutor.class, () -> "MADE_PRE_AUTH");
    }

    @Override
    public SagaExecutionEventName doRevert(NonRetryableExecutorException processException, PlaceOrderAggregator finalAggregatorState, RevertHintStore revertHintStore, String idempotencyKey) throws RetryableExecutorException {
        // TODO: execute pre-auth compensation process here by calling the payment-service
        return () -> "PRE_AUTH_RELEASED";
    }
}
