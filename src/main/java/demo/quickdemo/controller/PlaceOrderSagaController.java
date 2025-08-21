/*
package demo.quickdemo.controller;

import demo.quickdemo.PlaceOrderTopic;
import demo.quickdemo.aggregators.PlaceOrderAggregator;
import demo.quickdemo.event.PlaceOrderEvent;
import demo.quickdemo.event.PlaceOrderStepManager;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import stacksaga.async.core.kafka.SagaKafkaTemplate;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/order")
@AllArgsConstructor
public class PlaceOrderSagaController {

    private final SagaKafkaTemplate<PlaceOrderAggregator, PlaceOrderTopic> placeOrderSagaKafkaTemplate;

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Map<String, String> placeOrder() {

        PlaceOrderAggregator aggregator = new PlaceOrderAggregator();
        aggregator.setUsername("mafei");
        aggregator.setTotal(200.00);
        String orderId = this.placeOrderSagaKafkaTemplate.start(
                aggregator,
                PlaceOrderStepManager.class,
                PlaceOrderEvent.DO_USER_VALIDATE
        );
        return Collections.singletonMap(
                "order_id",
                orderId//OR aggregator.getAggregatorTransactionId()
        );
    }

}
*/
