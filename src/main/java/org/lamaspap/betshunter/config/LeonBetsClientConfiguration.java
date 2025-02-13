package org.lamaspap.betshunter.config;

import org.lamaspap.betshunter.client.LeonBetsClient;
import org.lamaspap.betshunter.config.properties.LeonBetsClientProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;

@Configuration
@EnableConfigurationProperties(LeonBetsClientProperties.class)
public class LeonBetsClientConfiguration {

    private final Retrofit.Builder retrofitBuilder;
    private final RetrofitConfig retrofitConfig;
    private final LeonBetsClientProperties properties;

    public LeonBetsClientConfiguration(Retrofit.Builder retrofitBuilder,
                                       RetrofitConfig retrofitConfig,
                                       LeonBetsClientProperties properties) {
        this.retrofitBuilder = retrofitBuilder;
        this.retrofitConfig = retrofitConfig;
        this.properties = properties;
    }

    @Bean
    public LeonBetsClient leonBetsClient() {
        return retrofitConfig.createClient(retrofitBuilder, properties.baseUrl(), LeonBetsClient.class);
    }
}
