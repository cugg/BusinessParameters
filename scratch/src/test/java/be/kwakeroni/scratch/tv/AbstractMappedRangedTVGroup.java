package be.kwakeroni.scratch.tv;

import be.kwakeroni.parameters.backend.inmemory.api.EntryData;
import be.kwakeroni.parameters.backend.inmemory.support.DefaultEntryData;
import be.kwakeroni.parameters.basic.backend.es.ElasticSearchQueryBasedRangedGroup;
import be.kwakeroni.parameters.basic.backend.inmemory.InmemoryMappedGroup;
import be.kwakeroni.parameters.basic.backend.inmemory.InmemoryRangedGroup;
import be.kwakeroni.parameters.basic.backend.inmemory.InmemorySimpleGroup;
import be.kwakeroni.parameters.basic.client.model.Mapped;
import be.kwakeroni.parameters.basic.client.model.Ranged;
import be.kwakeroni.parameters.basic.client.model.Simple;
import be.kwakeroni.parameters.basic.client.query.MappedQuery;
import be.kwakeroni.parameters.basic.client.query.RangedQuery;
import be.kwakeroni.parameters.basic.client.query.ValueQuery;
import be.kwakeroni.parameters.basic.client.support.Entries;
import be.kwakeroni.parameters.basic.type.Range;
import be.kwakeroni.parameters.basic.type.Ranges;
import be.kwakeroni.parameters.client.api.model.Entry;
import be.kwakeroni.parameters.client.api.model.Parameter;
import be.kwakeroni.parameters.client.api.query.Query;
import be.kwakeroni.parameters.definition.api.ParameterGroupDefinition;
import be.kwakeroni.scratch.tv.definition.AbstractMappedRangedTV;
import be.kwakeroni.test.factory.TestMap;

import java.util.HashMap;
import java.util.Map;

/**
 * (C) 2017 Maarten Van Puymbroeck
 */
public interface AbstractMappedRangedTVGroup extends AbstractMappedRangedTV {


    public static Entry entry(Dag day, Slot from, Slot to, String program) {
        return Entries.entryOf(DAY, day, SLOT, Range.of(from, to), PROGRAM, program);
    }


    // For test purposes
    public static EntryData entryData(Dag day, Slot from, Slot to, String program) {
        return DefaultEntryData.of(TestMap.of(
                DAY.getName(), day.toString(),
                SLOT.getName(), Ranges.toRangeString(from, to, Slot::toString),
                PROGRAM.getName(), program
        ));
    }

    public static Map<String, ?> entryData(Dag day, Slot from, Slot to, String program, boolean addRangeLimits) {
        if (addRangeLimits) {
            Map<String, Object> map = new HashMap<>(5);
            map.put(DAY.getName(), day.toString());
            map.put(SLOT.getName(), Ranges.toRangeString(from, to, Slot::toString));
            map.put(ElasticSearchQueryBasedRangedGroup.getFromParameter(SLOT.getName()), from.toInt());
            map.put(ElasticSearchQueryBasedRangedGroup.getToParameter(SLOT.getName()), to.toInt());
            map.put(PROGRAM.getName(), program);
            return map;
        } else {
            return entryData(day, from, to, program).asMap();
        }
    }

    // For test purposes
    public static Query<Mapped<Dag, Ranged<Slot, Simple>>, String> programQuery(Dag day, Slot slot) {
        return valueQuery(day, slot, AbstractMappedRangedTVGroup.PROGRAM);
    }

    public static <T> Query<Mapped<Dag, Ranged<Slot, Simple>>, T> valueQuery(Dag day, Slot slot, Parameter<T> parameter) {
        return new MappedQuery<>(day, Dag.type,
                new RangedQuery<>(slot, Slot.type,
                        new ValueQuery<>(parameter)));
    }


    static InmemoryMappedGroup inmemoryTestGroup(String name, ParameterGroupDefinition definition) {
        return new InmemoryMappedGroup(DAY.getName(), definition,
                new InmemoryRangedGroup(SLOT.getName(), Ranges.stringRangeTypeOf(Slot.type), definition,
                        new InmemorySimpleGroup(name, definition, DAY.getName(), SLOT.getName(), PROGRAM.getName())));
    }


}
