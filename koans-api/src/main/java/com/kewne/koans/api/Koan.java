package com.kewne.koans.api;

public interface Koan extends Comparable<Koan> {

    public interface Id {}

    Id getId();

    String getClassName();
    String getContent();
}
