package demo.quickdemo.aggregators;

import org.stacksaga.Aggregator;
import org.stacksaga.annotation.SagaAggregator;
import org.stacksaga.annotation.SagaAggregatorVersion;

@SagaAggregator(
        version = @SagaAggregatorVersion(major = 1, minor = 0, patch = 0),
        name = "AsyncPlaceOrderAggregator"
)
public class AsyncPlaceOrderAggregator extends Aggregator {
    public AsyncPlaceOrderAggregator() {
        super(AsyncPlaceOrderAggregator.class);
    }

    private String name;
    private String value1;
    private String value2;
    private String value3;
    private String isReadyToDelivery;

    public String getIsReadyToDelivery() {
        return isReadyToDelivery;
    }

    public void setIsReadyToDelivery(String isReadyToDelivery) {
        this.isReadyToDelivery = isReadyToDelivery;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue1() {
        return value1;
    }

    public void setValue1(String value1) {
        this.value1 = value1;
    }

    public String getValue2() {
        return value2;
    }

    public void setValue2(String value2) {
        this.value2 = value2;
    }

    public String getValue3() {
        return value3;
    }

    public void setValue3(String value3) {
        this.value3 = value3;
    }
}
