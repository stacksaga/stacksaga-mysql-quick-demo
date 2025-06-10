/*
package demo.quickdemo.event;

import stacksaga.async.core.SagaEvent;
import stacksaga.async.core.SagaEventType;

public enum PlaceOrderEvent implements SagaEvent<PlaceOrderEvent> {
    DO_MAKE_PAYMENT(1, "order-service", SagaEventType.COMMAND_DO_ACTION),
    UNDO_MAKE_PAYMENT(2, "order-service", SagaEventType.COMMAND_UNDO_ACTION, DO_MAKE_PAYMENT),
    UNDO_MAKE_PAYMENT_SUB_BEFORE_1(-2.1f, "order-service", SagaEventType.COMMAND_UNDO_BEFORE_ACTION, UNDO_MAKE_PAYMENT),
    UNDO_MAKE_PAYMENT_SUB_AFTER_1(2.1f, "order-service", SagaEventType.COMMAND_UNDO_AFTER_ACTION, UNDO_MAKE_PAYMENT),
    DO_UPDATE_STOCK(3, "order-service", SagaEventType.COMMAND_DO_ACTION),
    UNDO_UPDATE_STOCK(4, "order-service", SagaEventType.COMMAND_UNDO_ACTION, DO_UPDATE_STOCK),
    DO_MAKE_DELIVERY(5, "delivery-service", SagaEventType.COMMAND_DO_ACTION),
    UNDO_MAKE_DELIVERY(6, "delivery-service", SagaEventType.COMMAND_UNDO_ACTION, DO_MAKE_DELIVERY),
    UNDO_MAKE_DELIVERY_SUB_1(7, "delivery-service", SagaEventType.COMMAND_UNDO_AFTER_ACTION, UNDO_MAKE_DELIVERY),
    UNDO_MAKE_DELIVERY_SUB_2(8, "delivery-service", SagaEventType.COMMAND_UNDO_AFTER_ACTION, UNDO_MAKE_DELIVERY),
    DO_DO_SOME1(9, "order-service", SagaEventType.COMMAND_DO_ACTION),
    UNDO_DO_SOME1(10, "order-service", SagaEventType.COMMAND_UNDO_ACTION, DO_DO_SOME1),
    ;

    private final String targetServiceName;
    private final SagaEventType sagaEventType;
    private final PlaceOrderEvent revert;
    private final float eventKey;

    PlaceOrderEvent(float eventKey, String targetServiceName, SagaEventType sagaEventType, PlaceOrderEvent parent) {
        this.targetServiceName = targetServiceName;
        this.sagaEventType = sagaEventType;
        this.revert = parent;
        this.eventKey = eventKey;
    }

    PlaceOrderEvent(int eventKey, String targetServiceName, SagaEventType sagaEventType) {
        this.targetServiceName = targetServiceName;
        this.sagaEventType = sagaEventType;
        this.revert = null;
        this.eventKey = eventKey;
    }

    @Override
    public String targetService() {
        return this.targetServiceName;
    }

    @Override
    public SagaEventType eventType() {
        return this.sagaEventType;
    }

    @Override
    public PlaceOrderEvent parent() {
        return this.revert;
    }

    @Override
    public float eventKey() {
        return this.eventKey;
    }
}*/
