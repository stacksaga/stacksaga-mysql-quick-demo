package demo.quickdemo.aggregators;

import lombok.*;
import org.stacksaga.Aggregator;
import org.stacksaga.SagaSerializable;
import org.stacksaga.annotation.SagaAggregator;
import org.stacksaga.annotation.SagaAggregatorVersion;

@SagaAggregator(
        version = @SagaAggregatorVersion(major = 1, minor = 0, patch = 0),
        name = "PlaceOrderAggregator",
        sagaSerializable = PlaceOrderAggregatorSagaSerializable.class
)
@Getter
@Setter
public class PlaceOrderAggregator extends Aggregator {
    private String orderId;
    private double amount;
    private String userId;
    private boolean isPaid;
    private String paymentId;

    public PlaceOrderAggregator() {
        super(PlaceOrderAggregator.class);
    }

}

class PlaceOrderAggregatorSagaSerializable extends SagaSerializable<PlaceOrderAggregator> {
    public PlaceOrderAggregatorSagaSerializable() {
        this.put("Sample-1", new PlaceOrderAggregator());
    }
}
