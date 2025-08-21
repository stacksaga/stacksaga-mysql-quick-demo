//package demo.quickdemo.controller;
//
//import demo.quickdemo.aggregators.PlaceOrderAggregator;
//import demo.quickdemo.dto.PlaceOrderDto;
//import demo.quickdemo.executors.OrderInitializeExecutor;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.web.bind.annotation.*;
//import org.stacksaga.core.SagaTemplate;
////import stacksaga.async.core.StackSagaTemplateAsync;
//
//@Slf4j
//@RequestMapping()
//@RestController
//@RequiredArgsConstructor
//public class PlaceOrderController {
//
//    private final SagaTemplate<PlaceOrderAggregator> placeOrderAggregatorSagaTemplate;
//
//    @GetMapping("/place-non-reactive")
//    public PlaceOrderDto.Response placeOrder(
//            @RequestBody PlaceOrderDto.Request request
//    ) {
//        final PlaceOrderAggregator placeOrderAggregator = new PlaceOrderAggregator();
//        placeOrderAggregator.setAmount(10);
//        log.info("Placing order: {}", placeOrderAggregator);
//        final String orderId = this.placeOrderAggregatorSagaTemplate.process(placeOrderAggregator, OrderInitializeExecutor.class);
//        return new PlaceOrderDto.Response(orderId);
//    }
//
//
//    @GetMapping
//    public String placeOrder1() {
//        final PlaceOrderAggregator placeOrderAggregator = new PlaceOrderAggregator();
//        placeOrderAggregator.setAmount(1);
//        log.info("Placing order: {}", placeOrderAggregator);
//        return this.placeOrderAggregatorSagaTemplate.process(placeOrderAggregator, OrderInitializeExecutor.class);
//    }
//
//    @GetMapping("/sum")
//    public Object sum(@RequestParam("txId") String txId) {
//        return this.placeOrderAggregatorSagaTemplate.getTransactionSummary(txId);
//    }
//
//
//}
