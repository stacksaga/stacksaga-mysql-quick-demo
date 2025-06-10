/*
package demo.quickdemo.consumers;

import com.fasterxml.jackson.databind.JsonNode;
import org.apache.kafka.clients.consumer.ConsumerRecord;

import org.springframework.kafka.annotation.DltHandler;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.retry.annotation.Backoff;
import org.stacksaga.async.kafka.Endpoint;
import org.stacksaga.async.kafka.CommandEndpoint;
import org.stacksaga.async.kafka.KafkaListener;
import org.stacksaga.async.kafka.SagaRetryableException;

import java.util.Map;

@Endpoint(eventSuffix = "INIT_ORDER")
public class TestSagaEndpoint extends CommandEndpoint {

    @Override
    @RetryableTopic(attempts = "3", backoff = @Backoff(multiplier = 1.5), dltTopicSuffix = ".dlt")
    @KafkaListener("DO_INIT_ORDER")
    public void doProcess(ConsumerRecord<String, JsonNode> aggregator, @Headers Map<String, Object> headers, @Header(value = "LLL", required = false) String integer) throws SagaRetryableException {
        System.out.println("KafkaHeaders.LLL = " + integer);
        System.out.println("jsonNode = " + aggregator);
        System.out.println("headers = " + headers);

        System.out.println("done2");

//        aggregator.headers().add()
//        throw new SagaRetryableException();
        throw new RuntimeException();
    }



    @DltHandler
    @Override
    public void sendResponse(ConsumerRecord<String, JsonNode> record) {
        super.sendResponse(record);
    }

    @Override
    public void undoProcess(ConsumerRecord<String, JsonNode> aggregator, Map<String, Object> headers) throws SagaRetryableException {

    }
}
*/
