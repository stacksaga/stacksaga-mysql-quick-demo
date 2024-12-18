package demo.quickdemo.controller;

import demo.quickdemo.aggregators.PlaceOrderAggregator;
import demo.quickdemo.dto.PlaceOrderDto;
import demo.quickdemo.executors.PlaceOrderInitExecutor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.stacksaga.core.SagaTemplate;

@RequestMapping("/order")
@RestController
@RequiredArgsConstructor
public class PlaceOrderController {

    private final SagaTemplate<PlaceOrderAggregator> placeOrderAggregatorSagaTemplate;

    @PostMapping("/place")
    public PlaceOrderDto.Response placeOrder(@RequestBody PlaceOrderDto.Request request) {
        final PlaceOrderAggregator placeOrderAggregator = new PlaceOrderAggregator();
        placeOrderAggregator.setAmount(request.getAmount());
        final String orderId = this.placeOrderAggregatorSagaTemplate.process(placeOrderAggregator, PlaceOrderInitExecutor.class);
        return new PlaceOrderDto.Response(orderId);
    }
}
