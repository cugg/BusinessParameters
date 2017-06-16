package be.kwakeroni.parameters.backend.inmemory.factory;

import be.kwakeroni.parameters.definition.api.ParameterGroupDefinition;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * Created by kwakeroni on 08/06/17.
 */
public class ParameterGroupDefinitionContext implements Supplier<Stream<ParameterGroupDefinition>> {

    private final Map<String, ParameterGroupDefinition> definitions = new HashMap<>();

    public void register(ParameterGroupDefinition definition){
        this.definitions.merge(definition.getName(), definition,
                (a, b) -> { throw new IllegalStateException("Group definition " + a.getName() + " registered twice"); });
    }

    public void unregister(ParameterGroupDefinition definition, Map<Object, Object> properties) {
        if (definition != null) {
            this.definitions.remove(definition.getName(), definition);
        }
    }

    @Override
    public Stream<ParameterGroupDefinition> get() {
        return this.definitions.values().stream();
    }
}
