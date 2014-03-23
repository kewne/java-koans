package com.kewne.koans.api;

import java.util.List;

public interface Service {

    List<Koan> list(Integer startIndex, Integer maxEntries);

    Koan getByIndex(Integer index);

    boolean testResponse(Response response);
}
