package demo.quickdemo.executors;

import demo.quickdemo.aggregators.PlaceOrderAggregator;
import lombok.SneakyThrows;
import org.stacksaga.ProcessStepManager;
import org.stacksaga.ProcessStepManagerUtil;
import org.stacksaga.annotation.SagaExecutor;
import org.stacksaga.exception.RetryableExecutorException;
import org.stacksaga.exception.execution.NonRetryableExecutorException;
import org.stacksaga.executor.QueryExecutor;

import java.util.Random;

@SagaExecutor(
        executeFor = "user-service",
        value = "GetUserDetailsExecutor"
)
public class GetUserDetailsExecutor implements QueryExecutor<PlaceOrderAggregator> {

    @SneakyThrows
    @Override
    public ProcessStepManager<PlaceOrderAggregator> doProcess(
            PlaceOrderAggregator currentAggregator,
            ProcessStepManagerUtil<PlaceOrderAggregator> stepManager,
            String idempotencyKey
    ) throws RetryableExecutorException, NonRetryableExecutorException {

        // TODO: Get user details from user-service
        Thread.sleep(new Random().nextLong(1000, 3000));
        {
            //updates the aggregator with the user details
            currentAggregator.setUserId("mafei");
        }
        return stepManager.next(MakePaymentExecutor.class, () -> "PLACED_ORDER");
    }
}
