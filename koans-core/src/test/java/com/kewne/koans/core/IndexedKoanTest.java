package com.kewne.koans.core;

import com.kewne.koans.api.Koan;
import org.junit.Test;

import java.util.SortedSet;
import java.util.TreeSet;

import static org.junit.Assert.assertFalse;

public class IndexedKoanTest {

    @Test
    public void testHeadSet() {
        IndexedKoan k = new IndexedKoan(
                new Koan.Id() { },
                null,
                null,
                1
        );
        SortedSet<Koan> ks = new TreeSet<>();
        ks.add(k);
        SortedSet<Koan> listOfKs = ks.tailSet(
                new IndexedKoan.MockIndexedKoan(0)
        );
        assertFalse(listOfKs.isEmpty());
    }
}
