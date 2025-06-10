package demo.quickdemo.executors;

import demo.quickdemo.aggregators.PlaceOrderAggregator;
import org.stacksaga.RevertHintStore;
import org.stacksaga.annotation.SagaExecutor;
import org.stacksaga.exception.RetryableExecutorException;
import org.stacksaga.exception.execution.NonRetryableExecutorException;
import org.stacksaga.executor.RevertAfterExecutor;
import org.stacksaga.executor.utils.RevertAfterStepManager;
import org.stacksaga.executor.utils.RevertAfterStepManagerUtil;

@SagaExecutor(executeFor = "test-service", value = "InitOrderSubAfter1")
public class InitOrderSubAfter1 implements RevertAfterExecutor<PlaceOrderAggregator, OrderInitializeExecutor> {


    @Override
    public RevertAfterStepManager<PlaceOrderAggregator, OrderInitializeExecutor> doProcess(PlaceOrderAggregator finalAggregatorState, NonRetryableExecutorException processException, RevertHintStore revertHintStore, RevertAfterStepManagerUtil<PlaceOrderAggregator, OrderInitializeExecutor> stepManager, String idempotencyKey) throws RetryableExecutorException {
//        return stepManager.next(InitOrderSubAfter1.class,() -> "InitOrderSubAfter1");
        return stepManager.complete(() -> "InitOrderSubAfter1");
    }
}
