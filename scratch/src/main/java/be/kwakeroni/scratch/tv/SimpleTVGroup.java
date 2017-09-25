package be.kwakeroni.scratch.tv;

import be.kwakeroni.parameters.backend.inmemory.api.EntryData;
import be.kwakeroni.parameters.backend.inmemory.support.DefaultEntryData;
import be.kwakeroni.parameters.basic.backend.es.ElasticSearchSimpleGroup;
import be.kwakeroni.parameters.basic.backend.inmemory.InmemorySimpleGroup;
import be.kwakeroni.parameters.basic.client.model.Simple;
import be.kwakeroni.parameters.basic.client.support.Entries;
import be.kwakeroni.parameters.client.api.model.Entry;
import be.kwakeroni.parameters.client.api.model.Parameter;
import be.kwakeroni.parameters.client.api.model.ParameterGroup;
import be.kwakeroni.parameters.definition.api.ParameterGroupDefinition;

import static be.kwakeroni.parameters.basic.definition.BasicGroup.group;

/**
 * (C) 2016 Maarten Van Puymbroeck
 */
public class SimpleTVGroup implements ParameterGroup<Simple> {


    public static final SimpleTVGroup instance() {
        return new SimpleTVGroup();
    }


    @Override
    public String getName() {
        return NAME;
    }

    public static Parameter<Dag> DAY = new DefaultParameter<>("day", Dag.type);
    public static Parameter<Slot> SLOT = new DefaultParameter<>("slot", Slot.type);

    public static Entry entry(Dag dag, Slot slot) {
        return Entries.entryOf(DAY, dag, SLOT, slot);
    }

    // For test purposes
    public static final EntryData getEntryData(Dag dag, Slot slot) {
        return DefaultEntryData.of(
                DAY.getName(), dag.toString(),
                SLOT.getName(), slot.toString()
        );
    }

    public static final String NAME = "tv.simple";

    public static final ParameterGroupDefinition DEFINITION =
            group()
                    .withParameter(DAY.getName())
                    .withParameter(SLOT.getName())
                    .build(NAME);

    static final InmemorySimpleGroup INMEMORY_TEST_GROUP = new InmemorySimpleGroup(NAME, DEFINITION, DAY.getName(), SLOT.getName());

    static final ElasticSearchSimpleGroup ELASTICSEARCH_TEST_GROUP = new ElasticSearchSimpleGroup(NAME, DEFINITION, DAY.getName(), SLOT.getName());

}