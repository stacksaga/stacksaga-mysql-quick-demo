package demo.quickdemo.controller;

import demo.quickdemo.aggregators.AsyncPlaceOrderAggregator;
import demo.quickdemo.event.PlaceOrderEvent;
import demo.quickdemo.event.PlaceOrderStepManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.*;
import org.stacksaga.TransactionSummary;
import stacksaga.async.core.kafka.SagaKafkaTemplate;
import stacksaga.async.core.kafka.ReactiveSagaKafkaTemplate;

import java.util.concurrent.CompletableFuture;
//import stacksaga.async.core.StackSagaTemplateAsync;

@Slf4j
@RequestMapping()
@RestController
@RequiredArgsConstructor
public class PlaceOrderKafkaController {

    private final ReactiveSagaKafkaTemplate<AsyncPlaceOrderAggregator, PlaceOrderEvent> stackSagaTemplateAsync;
    private final SagaKafkaTemplate<AsyncPlaceOrderAggregator, PlaceOrderEvent> eventSagaKafkaTemplate;
    private final ApplicationContext applicationContext;

    @GetMapping("/")
    public CompletableFuture<String> placeOrder() {
        final AsyncPlaceOrderAggregator placeOrderAggregator = new AsyncPlaceOrderAggregator();
        placeOrderAggregator.setName("mafeo");
        log.info("Placing order: {}", placeOrderAggregator);
        return this.stackSagaTemplateAsync
                .start(placeOrderAggregator, PlaceOrderStepManager.class, PlaceOrderEvent.DO_USER_VALIDATE)
                .toFuture();
//        return new PlaceOrderDto.Response(orderId);
    }

    //    @GetMapping
//    public String placeOrder1() {
//        final PlaceOrderAggregator placeOrderAggregator = new PlaceOrderAggregator();
//        placeOrderAggregator.setAmount(1);
//        log.info("Placing order: {}", placeOrderAggregator);
//        return this.placeOrderAggregatorSagaTemplate.process(placeOrderAggregator, OrderInitializeExecutor.class);
//    }
//
    @GetMapping("/sum")
    public CompletableFuture<TransactionSummary<AsyncPlaceOrderAggregator>> sum(@RequestParam("txId") String txId) {
        return this.stackSagaTemplateAsync.getTransactionSummary(txId).toFuture();
    }
//


}
