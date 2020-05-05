package ro.msg.learning.shop.configuration;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ro.msg.learning.shop.configuration.strategies.IDeliveryStrategy;
import ro.msg.learning.shop.configuration.strategies.MostAbundantStrategy;
import ro.msg.learning.shop.configuration.strategies.SingleLocationStrategy;
import ro.msg.learning.shop.configuration.strategies.StrategiesEnum;

@Data
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties("app")
@RequiredArgsConstructor
public class DeliveryStrategyConfiguration {

    private String deliveryStrategy;

    private final MostAbundantStrategy mostAbundantStrategy;
    private final SingleLocationStrategy singleLocationStrategy;

    @Bean
    public IDeliveryStrategy getDeliveryStrategy() {
        StrategiesEnum strategyValue;
        try {
            strategyValue = StrategiesEnum.valueOf(deliveryStrategy.toUpperCase());
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
