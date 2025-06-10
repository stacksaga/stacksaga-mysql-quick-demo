package demo.quickdemo.executors;

import demo.quickdemo.aggregators.PlaceOrderAggregator;
import org.stacksaga.RevertHintStore;
import org.stacksaga.annotation.SagaExecutor;
import org.stacksaga.exception.execution.NonRetryableExecutorException;
import org.stacksaga.executor.ReactiveRevertBeforeExecutor;
import org.stacksaga.executor.utils.RevertBeforeStepManager;
import org.stacksaga.executor.utils.RevertBeforeStepManagerUtil;
import reactor.core.publisher.Mono;

@SagaExecutor(executeFor = "same", value = "PreAuthExecutorBefore1")
public class PreAuthExecutorBefore1 implements ReactiveRevertBeforeExecutor<PlaceOrderAggregator, PreAuthExecutor> {
    @Override
    public Mono<RevertBeforeStepManager<PlaceOrderAggregator, PreAuthExecutor>> doProcess(PlaceOrderAggregator aggregator, NonRetryableExecutorException processException, RevertHintStore revertHintStore, RevertBeforeStepManagerUtil<PlaceOrderAggregator, PreAuthExecutor> stepManagerUtil, String idempotencyKey) {
//        throw new RuntimeException("PreAuthExecutorBefore1");
        return Mono.just(stepManagerUtil.complete(() -> "PRE_AUTH_EXECUTOR_BEFORE1"));
//        return Mono.empty();
    }
}
