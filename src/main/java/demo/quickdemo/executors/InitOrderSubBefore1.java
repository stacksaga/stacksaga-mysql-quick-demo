package demo.quickdemo.executors;

import demo.quickdemo.aggregators.PlaceOrderAggregator;
import org.stacksaga.RevertHintStore;
import org.stacksaga.annotation.SagaExecutor;
import org.stacksaga.exception.RetryableExecutorException;
import org.stacksaga.exception.execution.NonRetryableExecutorException;
import org.stacksaga.executor.RevertBeforeExecutor;
import org.stacksaga.executor.utils.RevertBeforeStepManager;
import org.stacksaga.executor.utils.RevertBeforeStepManagerUtil;

@SagaExecutor(executeFor = "test-service", value = "InitOrderSubBefore1")
public class InitOrderSubBefore1 implements RevertBeforeExecutor<PlaceOrderAggregator, OrderInitializeExecutor> {


    @Override
    public RevertBeforeStepManager<PlaceOrderAggregator, OrderInitializeExecutor> doProcess(PlaceOrderAggregator aggregator, NonRetryableExecutorException processException, RevertHintStore revertHintStore, RevertBeforeStepManagerUtil<PlaceOrderAggregator, OrderInitializeExecutor> stepManagerUtil, String idempotencyKey) throws RetryableExecutorException {
        return stepManagerUtil.next(InitOrderSubBefore2.class, () -> "done-1");
    }
}
