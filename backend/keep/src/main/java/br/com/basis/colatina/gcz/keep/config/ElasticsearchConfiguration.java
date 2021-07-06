package br.com.basis.colatina.gcz.keep.config;

import br.com.basis.colatina.gcz.keep.config.property.ApplicationProperties;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;

import java.io.IOException;

@RequiredArgsConstructor
@Configuration
public class ElasticsearchConfiguration extends AbstractElasticsearchConfiguration {

    private final ApplicationProperties applicationProperties;


    @Override
    @NonNull
    @Bean
    public RestHighLevelClient elasticsearchClient() {
        final var clientConfiguration = ClientConfiguration.builder()
                .connectedTo(applicationProperties.getElasticsearch().getUrl())
                .build();
        RestHighLevelClient restHighLevelClient = null;

        try (var restClient = RestClients.create(clientConfiguration)) {
            restHighLevelClient = restClient.rest();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return restHighLevelClient;
    }

}
