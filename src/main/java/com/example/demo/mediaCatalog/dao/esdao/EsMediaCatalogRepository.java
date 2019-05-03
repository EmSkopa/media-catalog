package com.example.demo.mediaCatalog.dao.esdao;

import com.example.demo.mediaCatalog.model.es.EsMediaCatalog;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EsMediaCatalogRepository extends ElasticsearchRepository<EsMediaCatalog, UUID> {
}
