package org.lamaspap.betshunter.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

@Configuration
public class RetrofitConfig {

    @Bean
    public Retrofit.Builder retrofitBuilder(ObjectMapper objectMapper) {
        return new Retrofit.Builder()
                .addConverterFactory(JacksonConverterFactory.create(objectMapper));
    }

    public <T> T createClient(Retrofit.Builder builder, String baseUrl, Class<T> clientClass) {
        Retrofit retrofit = builder.baseUrl(baseUrl).build();
        return retrofit.create(clientClass);
    }
}