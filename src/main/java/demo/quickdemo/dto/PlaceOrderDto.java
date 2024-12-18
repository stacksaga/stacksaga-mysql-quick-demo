package demo.quickdemo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

public class PlaceOrderDto {
    @Data
    public static class Request {
        private double amount;
    }

    @Data
    @AllArgsConstructor
    public static class Response {
        @JsonProperty("order_id")
        private final String orderId;
    }
}
