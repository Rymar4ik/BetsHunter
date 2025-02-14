package org.lamaspap.betshunter.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import org.lamaspap.betshunter.config.properties.LeonBetsClientProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.util.concurrent.TimeUnit;

@Configuration
public class RetrofitConfig {

    private final LeonBetsClientProperties clientProperties;

    public RetrofitConfig(LeonBetsClientProperties clientProperties) {
        this.clientProperties = clientProperties;
    }

    @Bean
    public Retrofit.Builder retrofitBuilder(ObjectMapper objectMapper) {
        return new Retrofit.Builder()
                .addConverterFactory(JacksonConverterFactory.create(objectMapper))
                .client(okHttpClient());
    }

    private OkHttpClient okHttpClient() {
        return new OkHttpClient.Builder()
                .connectTimeout(clientProperties.connectTimeout(), TimeUnit.MILLISECONDS)
                .readTimeout(clientProperties.readTimeout(), TimeUnit.MILLISECONDS)
                .writeTimeout(clientProperties.writeTimeout(), TimeUnit.MILLISECONDS)
                .build();
    }

    public <T> T createClient(Retrofit.Builder builder, String baseUrl, Class<T> clientClass) {
        Retrofit retrofit = builder.baseUrl(baseUrl).build();
        return retrofit.create(clientClass);
    }
}