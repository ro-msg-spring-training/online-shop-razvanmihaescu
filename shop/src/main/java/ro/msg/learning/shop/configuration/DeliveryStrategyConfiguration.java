package ro.msg.learning.shop.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DeliveryStrategyConfiguration {

    @Value("${delivery_strategy}")
    private String propertyValue;

    @Bean
    public IDeliveryStrategy getDeliveryStrategy() {
        StrategiesEnum strategyValue;
        try {
            strategyValue = StrategiesEnum.valueOf(propertyValue.toUpperCase());
        } catch (IllegalArgumentException e) {
            strategyValue = StrategiesEnum.SINGLE_LOCATION;
        }
        if (strategyValue == StrategiesEnum.MOST_ABUNDANT) {
            return new MostAbundantStrategy();
        } else {
            return new SingleLocationStrategy();
        }
    }
}

