package com.kewne.koans.core;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.kewne.koans.api.*;
import com.kewne.koans.api.Koan;

import java.util.*;

public class CoreService implements Service {

    private final List<Koan> koans;
    private final KoanFactory koanFactory = new KoanFactory();

    public CoreService(List<Koan> koans) { this.koans = Collections.unmodifiableList(koans); }

    @Override
    public List<Koan> list(Integer startIndex, Integer maxEntries) {
        if (startIndex > koans.size()) { return Collections.emptyList(); }
        int endIndex = rangeExceedsEnd(startIndex, maxEntries) ? koans.size() : maxEntries;
        return koans.subList(startIndex, endIndex);
    }

    private boolean rangeExceedsEnd(Integer startIndex, Integer maxEntries) {
        return (maxEntries == null) || ((startIndex + maxEntries) > koans.size());
    }

    @Override
    public Koan getByIndex(Integer index) {
        return koans.get(index);
    }

    @Override
    public boolean testResponse(Response response) {
        Koan k = Iterables.find(koans, new FindKoanById(response.getId()));
        String responseString = String.format(Locale.ROOT, k.getContent(), response.getContent());
        Class<?> cls = koanFactory.compileAndLoad(
                new InMemoryJavaSourceObject(
                        k.getClassName(),
                        responseString
                )
        );
        return cls.getName().equals(k.getClassName());
    }

    private static final class FindKoanById implements Predicate<Koan> {

        private final Koan.Id id;

        private FindKoanById(Koan.Id id) { this.id = id; }

        @Override
        public boolean apply(Koan koan) {
            return id.equals(koan.getId());
        }
    }
}
