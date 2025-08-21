/*
package demo.quickdemo;

import demo.quickdemo.aggregators.PlaceOrderAggregator;
import org.stacksaga.RevertHintStore;
import org.stacksaga.exception.execution.NonRetryableExecutorException;
import stacksaga.async.core.SagaEventNavigator;
import stacksaga.async.core.SagaStepExecutor;
import stacksaga.async.core.service.SagaPrimaryEventAction;
import stacksaga.async.core.service.SagaRevertEventAction;

@SagaStepExecutor(rootTopic = "PlaceOrderStepManager", value = "PlaceOrderStepManager", partitions = {0})
public class PlaceOrderStepManager implements SagaEventNavigator<PlaceOrderAggregator, PlaceOrderTopic> {
    @Override
    public SagaPrimaryEventAction<PlaceOrderTopic> onNext(PlaceOrderTopic recentTopic, PlaceOrderAggregator aggregator) {
        return switch (recentTopic) {
            case DO_USER_VALIDATE -> SagaPrimaryEventAction.next(PlaceOrderTopic.DO_UPDATE_STOCK);
            case DO_UPDATE_STOCK -> SagaPrimaryEventAction.next(PlaceOrderTopic.DO_MAKE_PAYMENT);
            case DO_MAKE_DELIVERY -> SagaPrimaryEventAction.done();
            default -> SagaPrimaryEventAction.error(new IllegalStateException("Unexpected value: " + recentTopic));
        };
    }

    @Override
    public SagaRevertEventAction<PlaceOrderTopic> onNextRevert(PlaceOrderTopic lastEvent, PlaceOrderAggregator aggregator, NonRetryableExecutorException exception, RevertHintStore revertHintStore) {
        return switch (lastEvent) {
            case UNDO_UPDATE_STOCK -> SagaRevertEventAction.next(PlaceOrderTopic.UNDO_MAKE_PAYMENT_SUB_BEFORE_1);
            case UNDO_MAKE_PAYMENT_SUB_BEFORE_1 -> SagaRevertEventAction.next(PlaceOrderTopic.UNDO_MAKE_PAYMENT_SUB_BEFORE_2);
            case UNDO_MAKE_PAYMENT -> SagaRevertEventAction.next(PlaceOrderTopic.UNDO_MAKE_PAYMENT_SUB_AFTER_1);
            default -> SagaRevertEventAction.autopilot();
        };
    }
}
*/
