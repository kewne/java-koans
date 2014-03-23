package com.kewne.koans.core;

import com.kewne.koans.api.Koan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;
import java.util.Objects;

public final class IndexedKoan implements Koan {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private final Id id;
    private final String koanString;
    private final int order;
    private final String className;

    public IndexedKoan(Id id, String className, String koanString, int order) {
        this.id = id;
        this.koanString = koanString;
        this.order = order;
        this.className = className;
    }

    @Override
    public Id getId() { return id; }

    @Override
    public String getClassName() {
        return className;
    }

    @Override
    public String getContent() { return koanString; }

    @Override
    public int compareTo(Koan o) {
        if (o instanceof MockIndexedKoan) {
            MockIndexedKoan mK = (MockIndexedKoan) o;
            return order - mK.index;
        } else if (o instanceof IndexedKoan) {
            IndexedKoan iK = (IndexedKoan) o;
            int diff = order - iK.order;
            if (diff == 0 && !equals(iK)) {
                LOGGER.warn(
                        "Found two koans with the same order! Class is '{}', ids are '{}','{}'",
                        MethodHandles.lookup().lookupClass(),
                        this.id,
                        iK.id
                );
            }
            return diff;
        }
        throw new IllegalStateException("");
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) { return false; }

        IndexedKoan that = (IndexedKoan) o;
        return id.equals(that.id);

    }

    @Override
    public int hashCode() { return Objects.hash(id); }

    final static class MockIndexedKoan implements Koan {

        private final int index;

        MockIndexedKoan(int index) { this.index = index; }

        @Override
        public Id getId() { throw new UnsupportedOperationException(); }

        @Override
        public String getClassName() { throw new UnsupportedOperationException(); }

        @Override
        public String getContent() { throw new UnsupportedOperationException(); }

        @Override
        public int compareTo(Koan o) {
            if (o instanceof IndexedKoan) { return -o.compareTo(this);  }
            if (o instanceof MockIndexedKoan) { return 0; }
            throw new IllegalStateException(
                    "Attempted comparison with non '" + IndexedKoan.class + " 'instance"
            );
        }
    }

}
