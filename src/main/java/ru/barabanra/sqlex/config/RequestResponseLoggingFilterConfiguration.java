package ru.barabanra.sqlex.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import ru.barabanra.sqlex.dto.properties.RequestResponseFilterProperties;
import ru.barabanra.sqlex.web.filter.RequestResponseLoggingFilter;

@Configuration
public class RequestResponseLoggingFilterConfiguration {

    @Bean
    @ConfigurationProperties("request-response-logger-filter")
    public RequestResponseFilterProperties graylogProperties() {
        return new RequestResponseFilterProperties();
    }

    @Bean
    public RequestResponseLoggingFilter requestResponseLoggingFilter(RequestResponseFilterProperties properties) {
        return new RequestResponseLoggingFilter(properties.getMaxPayLoad());
    }

    @Bean
    public FilterRegistrationBean<RequestResponseLoggingFilter> requestResponseLoggingFilterRegistration(RequestResponseLoggingFilter filter,
                                                                                                         RequestResponseFilterProperties properties) {
        FilterRegistrationBean<RequestResponseLoggingFilter> filterRegistration = new FilterRegistrationBean<>();
        filterRegistration.setFilter(filter);
        filterRegistration.setUrlPatterns(properties.getEndpoint());
        filterRegistration.setOrder(Ordered.LOWEST_PRECEDENCE - 2);
        return filterRegistration;
    }

}