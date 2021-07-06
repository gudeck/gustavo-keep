package br.com.basis.colatina.gcz.keep.service.elasticsearch;

import br.com.basis.colatina.gcz.keep.repository.elasticsearch.Reindexator;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializer;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.substringBetween;
import static org.elasticsearch.client.RequestOptions.DEFAULT;
import static org.elasticsearch.common.xcontent.XContentType.JSON;

@RequiredArgsConstructor
@Service
public class ElasticsearchService {
    private final RestHighLevelClient restHighLevelClient;

    private final ElasticsearchOperations elasticsearchOperations;

    private final List<Reindexator<?>> reindexators;

    public void reindexAll() {
        reindexators.forEach(this::reindex);

    }

    public void reindexOne(String indexName) {
        reindexators.stream()
                .filter(reindexator -> getIndexNameByReindexator(reindexator).equals(indexName))
                .findFirst()
                .ifPresent(this::reindex);
    }

    private void reindex(Reindexator<?> reindexator) {
        final var documents = reindexator.getDocuments(Pageable.ofSize(500));
        final var documentClass = getDocumentClass(documents);

        restartIndex(documentClass);
        populateIndex(documentClass, documents, reindexator);
    }

    private String getIndexNameByReindexator(Reindexator<?> reindexator) {
        return elasticsearchOperations.getIndexCoordinatesFor(getDocumentClass(reindexator.getDocuments(Pageable.ofSize(1)))).getIndexName();
    }

    private Class<?> getDocumentClass(Page<?> documents) {
        return documents.getContent().get(0).getClass();
    }

    private void restartIndex(Class<?> documentClass) {
        elasticsearchOperations.indexOps(documentClass).delete();
        elasticsearchOperations.indexOps(documentClass).create();
    }

    private void populateIndex(Class<?> documentClass, Page<?> initialDocuments, Reindexator<?> reindexator) {
        for (var documents = initialDocuments; documents.hasContent(); documents = reindexator.getDocuments(documents.getPageable().next())) {
            documents.forEach(document -> indexDocument(documentClass, document));
        }
    }

    @SneakyThrows
    private void indexDocument(Class<?> documentClass, Object document) {
        final var indexRequest = new IndexRequest(getIndexNameByClass(documentClass));

        final String documentJson = convertDocumentToJson(documentClass, document);
        indexRequest
                .id(substringBetween(documentJson, "\"id\":", ","))
                .source(documentJson, JSON);
        restHighLevelClient.index(indexRequest, DEFAULT);
    }

    private String getIndexNameByClass(Class<?> documentClass) {
        return elasticsearchOperations.getIndexCoordinatesFor(documentClass).getIndexName();
    }

    private String convertDocumentToJson(Class<?> documentClass, Object document) {
        final var gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, getLocalDateSerializer())
                .registerTypeAdapter(LocalDateTime.class, getLocalDateTimeSerializer()).create();

        return gson.toJson(document, documentClass);
    }

    private JsonSerializer<LocalDate> getLocalDateSerializer() {
        return (localDate, type, jsonSerializationContext) -> new JsonPrimitive(localDate.toString());
    }

    private JsonSerializer<LocalDateTime> getLocalDateTimeSerializer() {
        return (localDateTime, type, jsonSerializationContext) -> new JsonPrimitive(localDateTime.toString());
    }

}
