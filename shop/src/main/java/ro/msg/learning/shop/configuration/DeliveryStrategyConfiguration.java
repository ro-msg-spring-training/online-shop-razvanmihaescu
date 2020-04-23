package ro.msg.learning.shop.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ro.msg.learning.shop.configuration.strategies.IDeliveryStrategy;
import ro.msg.learning.shop.configuration.strategies.MostAbundantStrategy;
import ro.msg.learning.shop.configuration.strategies.SingleLocationStrategy;
import ro.msg.learning.shop.configuration.strategies.StrategiesEnum;

@Configuration
@RequiredArgsConstructor
public class DeliveryStrategyConfiguration {

    @Value("${delivery_strategy}")
    private String propertyValue;

    private final MostAbundantStrategy mostAbundantStrategy;
    private final SingleLocationStrategy singleLocationStrategy;

    @Bean
    public IDeliveryStrategy getDeliveryStrategy() {
        StrategiesEnum strategyValue;
        try {
            strategyValue = StrategiesEnum.valueOf(propertyValue.toUpperCase());
        } catch (IllegalArgumentException e) {
            strategyValue = StrategiesEnum.SINGLE_LOCATION;
        }
        if (strategyValue == StrategiesEnum.MOST_ABUNDANT) {
            return mostAbundantStrategy;
        } else {
            return singleLocationStrategy;
        }
    }
}
