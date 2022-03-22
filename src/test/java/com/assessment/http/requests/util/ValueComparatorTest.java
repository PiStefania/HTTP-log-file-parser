package com.assessment.http.requests.util;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ValueComparatorTest {

    @Test
    void compareObjects() {
        Map<String, Integer> base = new HashMap<>();
        base.put("a", 1);
        base.put("b", 2);
        ValueComparator valueComparator = new ValueComparator(base);
        TreeMap<String, Integer> sortedMap = new TreeMap<>(valueComparator);
        sortedMap.putAll(base);
        List<String> keys = new ArrayList<>();
        List<Integer> values = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : sortedMap.entrySet()) {
            keys.add(entry.getKey());
            values.add(entry.getValue());
        }
        assertEquals(2, keys.size());
        assertEquals(2, values.size());
        assertEquals("b", keys.get(0));
        assertEquals(2, values.get(0));
    }
}
