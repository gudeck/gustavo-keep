package br.com.basis.colatina.gcz.keep.config;

import br.com.basis.colatina.gcz.keep.config.propertie.ApplicationProperties;
import lombok.RequiredArgsConstructor;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;

@RequiredArgsConstructor
@Configuration
public class ElasticsearchConfiguration extends AbstractElasticsearchConfiguration {

    private final ApplicationProperties applicationProperties;

    @Override
    @Bean
    public RestHighLevelClient elasticsearchClient() {
        final var clientConfiguration = ClientConfiguration.builder()
                .connectedTo(applicationProperties.getElasticsearch().getUrl())
                .build();

        return RestClients.create(clientConfiguration).rest();
    }

}
