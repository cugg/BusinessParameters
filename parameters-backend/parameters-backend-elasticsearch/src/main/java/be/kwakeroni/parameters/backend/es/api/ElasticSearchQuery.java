package be.kwakeroni.parameters.backend.es.api;

import be.kwakeroni.parameters.backend.api.query.BackendQuery;
import org.json.JSONObject;

import java.util.Optional;

/**
 * (C) 2017 Maarten Van Puymbroeck
 */
public interface ElasticSearchQuery<T> extends BackendQuery<ElasticSearchQuery<T>, T> {

    @Override
    default ElasticSearchQuery<T> raw() {
        return this;
    }

    Optional<T> apply(ElasticSearchData data, ElasticSearchCriteria criteria);
}
