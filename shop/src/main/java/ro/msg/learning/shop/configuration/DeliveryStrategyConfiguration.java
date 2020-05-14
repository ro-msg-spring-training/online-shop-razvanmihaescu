package ro.msg.learning.shop.configuration;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ro.msg.learning.shop.configuration.strategies.*;

@Data
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties("app")
@RequiredArgsConstructor
public class DeliveryStrategyConfiguration {

    private String deliveryStrategy;

    private final MostAbundantStrategy mostAbundantStrategy;
    private final SingleLocationStrategy singleLocationStrategy;
    private final GreedyStrategy greedyStrategy;

    @Bean
    public IDeliveryStrategy getDeliveryStrategy() {
        StrategiesEnum strategyValue;
        strategyValue = StrategiesEnum.valueOf(deliveryStrategy.toUpperCase());
        switch (strategyValue) {
            case MOST_ABUNDANT:
                return mostAbundantStrategy;
            case GREEDY:
                return greedyStrategy;

            default:
                return singleLocationStrategy;
        }
    }
}
