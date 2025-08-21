package demo.quickdemo.aggregators;

import lombok.Getter;
import lombok.Setter;
import org.stacksaga.Aggregator;
import org.stacksaga.annotation.SagaAggregator;
import org.stacksaga.annotation.SagaAggregatorVersion;

@SagaAggregator(
        version = @SagaAggregatorVersion(major = 1, minor = 0, patch = 0),
        name = "PlaceOrderAggregator"
)
@Getter
@Setter
public class PlaceOrderAggregator extends Aggregator {
    private String orderId;
    private double amount;
    private double total;
    private String userId;
    private String username;
    private boolean isPaid;
    private String preAuthRefId;
    private String paymentId;

    public PlaceOrderAggregator() {
        super(PlaceOrderAggregator.class);
    }

}
