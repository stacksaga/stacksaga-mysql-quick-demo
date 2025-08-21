package demo.quickdemo.event;

import demo.quickdemo.aggregators.AsyncPlaceOrderAggregator;
import org.stacksaga.RevertHintStore;
import org.stacksaga.exception.execution.NonRetryableExecutorException;
import stacksaga.async.core.SagaEventNavigator;
import stacksaga.async.core.SagaStepExecutor;
import stacksaga.async.core.service.SagaPrimaryEventAction;
import stacksaga.async.core.service.SagaRevertEventAction;

@SagaStepExecutor(rootTopicSuffix = "PlaceOrderStepManager", value = "PlaceOrderStepManager")
public class PlaceOrderStepManager implements SagaEventNavigator<AsyncPlaceOrderAggregator, PlaceOrderEvent> {

    @Override
    public SagaPrimaryEventAction<PlaceOrderEvent> onNext(PlaceOrderEvent lastEvent, AsyncPlaceOrderAggregator aggregator) {
        return switch (lastEvent) {
            case DO_MAKE_PAYMENT -> {
                aggregator.setName("test name");
                yield SagaPrimaryEventAction.next(PlaceOrderEvent.DO_UPDATE_STOCK);
            }
            case DO_UPDATE_STOCK -> {
                aggregator.setIsReadyToDelivery("yes i am ready");
                yield SagaPrimaryEventAction.next(PlaceOrderEvent.DO_MAKE_DELIVERY);
                /*yield SagaPrimaryEventAction.error(PlaceOrderEvent.DO_MAKE_DELIVERY,
                        NonRetryableExecutorException
                                .buildWith(new RuntimeException("something went wrong."))
                                .put("message", "something went wrong")
                                .put("message1", "something went wrong")
                                .build()
                );*/


            }
            case DO_MAKE_DELIVERY -> {
                aggregator.setIsReadyToDelivery("yes i am ready");
                //todo: 30/05/2025 20:25 test with undo action
                yield SagaPrimaryEventAction.next(PlaceOrderEvent.DO_DO_SOME1);
                /*yield SagaPrimaryEventAction.error(PlaceOrderEvent.DO_MAKE_DELIVERY,
                        NonRetryableExecutorException
                                .buildWith(new RuntimeException("something went wrong."))
                                .put("message", "something went wrong")
                                .put("message1", "something went wrong")
                                .build()
                );
*/

            }
            case DO_DO_SOME1 -> {
                aggregator.setIsReadyToDelivery("yes i am ready2");
                //todo: 30/05/2025 20:25 test with undo action
                yield SagaPrimaryEventAction.next(PlaceOrderEvent.DO_DO_SOME2);
                /*yield SagaPrimaryEventAction.error(PlaceOrderEvent.DO_MAKE_DELIVERY,
                        NonRetryableExecutorException
                                .buildWith(new RuntimeException("something went wrong."))
                                .put("message", "something went wrong")
                                .put("message1", "something went wrong")
                                .build()
                );
*/

            }

            default -> SagaPrimaryEventAction.done();
        };
    }

    @Override
    public SagaRevertEventAction<PlaceOrderEvent> onNextRevert(PlaceOrderEvent lastEvent,
                                                               AsyncPlaceOrderAggregator aggregator,
                                                               NonRetryableExecutorException exception,
                                                               RevertHintStore revertHintStore) {
        System.out.println("lastEvent = " + lastEvent);
        return switch (lastEvent) {
            case UNDO_UPDATE_STOCK -> {
                yield SagaRevertEventAction.next(PlaceOrderEvent.UNDO_MAKE_PAYMENT_SUB_BEFORE_1);
            }
            case UNDO_MAKE_PAYMENT_SUB_BEFORE_1 -> {
                yield SagaRevertEventAction.next(PlaceOrderEvent.UNDO_MAKE_PAYMENT_SUB_BEFORE_2);
            }
            case UNDO_MAKE_PAYMENT -> {
                yield SagaRevertEventAction.next(PlaceOrderEvent.UNDO_MAKE_PAYMENT_SUB_AFTER_1);
            }
            default -> SagaRevertEventAction.autopilot();
        };
    }


}
