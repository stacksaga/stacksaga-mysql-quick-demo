package demo.quickdemo;

import stacksaga.async.core.SagaTopic;
import stacksaga.async.core.SagaTopicType;

public enum PlaceOrderTopic implements SagaTopic<PlaceOrderTopic> {
    DO_USER_VALIDATE(1, "user-service", SagaTopicType.QUERY_DO_ACTION),
    DO_MAKE_PAYMENT(1, "order-service", SagaTopicType.COMMAND_DO_ACTION),
    UNDO_MAKE_PAYMENT(2, "order-service", SagaTopicType.COMMAND_UNDO_ACTION, DO_MAKE_PAYMENT),
    UNDO_MAKE_PAYMENT_SUB_BEFORE_1(-2.1f, "order-service", SagaTopicType.COMMAND_UNDO_BEFORE_ACTION, UNDO_MAKE_PAYMENT),
    UNDO_MAKE_PAYMENT_SUB_BEFORE_2(-2.2f, "order-service", SagaTopicType.COMMAND_UNDO_BEFORE_ACTION, UNDO_MAKE_PAYMENT),
    UNDO_MAKE_PAYMENT_SUB_AFTER_1(2.1f, "order-service", SagaTopicType.COMMAND_UNDO_AFTER_ACTION, UNDO_MAKE_PAYMENT),
    DO_UPDATE_STOCK(3, "order-service", SagaTopicType.COMMAND_DO_ACTION),
    UNDO_UPDATE_STOCK(4, "order-service", SagaTopicType.COMMAND_UNDO_ACTION, DO_UPDATE_STOCK),
    DO_MAKE_DELIVERY(5, "delivery-service", SagaTopicType.COMMAND_DO_ACTION),
    UNDO_MAKE_DELIVERY(6, "delivery-service", SagaTopicType.COMMAND_UNDO_ACTION, DO_MAKE_DELIVERY),
    ;

    private final String targetServiceName;
    private final SagaTopicType sagaEventType;
    private final PlaceOrderTopic revert;
    private final float topicKey;

    PlaceOrderTopic(float topicKey, String targetServiceName, SagaTopicType sagaEventType, PlaceOrderTopic parent) {
        this.targetServiceName = targetServiceName;
        this.sagaEventType = sagaEventType;
        this.revert = parent;
        this.topicKey = topicKey;
    }

    PlaceOrderTopic(float topicKey, String targetServiceName, SagaTopicType sagaEventType) {
        this.targetServiceName = targetServiceName;
        this.sagaEventType = sagaEventType;
        this.revert = null;
        this.topicKey = topicKey;
    }

    @Override
    public String targetService() {
        return this.targetServiceName;
    }

    @Override
    public SagaTopicType topicType() {
        return this.sagaEventType;
    }

    @Override
    public PlaceOrderTopic parent() {
        return this.revert;
    }

    @Override
    public float topicKey() {
        return this.topicKey;
    }
}
