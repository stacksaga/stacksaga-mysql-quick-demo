package demo.quickdemo.executors;

import demo.quickdemo.aggregators.PlaceOrderAggregator;
import org.stacksaga.RevertHintStore;
import org.stacksaga.annotation.SagaExecutor;
import org.stacksaga.exception.execution.NonRetryableExecutorException;
import org.stacksaga.executor.ReactiveRevertBeforeExecutor;
import org.stacksaga.executor.utils.RevertBeforeStepManager;
import org.stacksaga.executor.utils.RevertBeforeStepManagerUtil;
import reactor.core.publisher.Mono;

@SagaExecutor(executeFor = "test-service", value = "InitOrderSubBefore2")
public class InitOrderSubBefore2 implements ReactiveRevertBeforeExecutor<PlaceOrderAggregator, OrderInitializeExecutor> {


    @Override
    public Mono<RevertBeforeStepManager<PlaceOrderAggregator, OrderInitializeExecutor>> doProcess(
            PlaceOrderAggregator aggregator,
            NonRetryableExecutorException processException,
            RevertHintStore revertHintStore,
            RevertBeforeStepManagerUtil<PlaceOrderAggregator, OrderInitializeExecutor> stepManagerUtil, String idempotencyKey) {
        return Mono.just(stepManagerUtil.complete(() -> "FUCK"));
    }
}
