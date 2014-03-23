package com.kewne.koans.core;

import javax.tools.SimpleJavaFileObject;
import java.io.IOException;
import java.net.URI;

public class InMemoryJavaSourceObject extends SimpleJavaFileObject {

    private final String code;
    private final String name;

    protected InMemoryJavaSourceObject(String qualifiedName, String code) {
        super(URI.create("string://" + qualifiedName.replace('.', '/') + Kind.SOURCE.extension), Kind.SOURCE);
        this.code = code;
        this.name = qualifiedName;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public CharSequence getCharContent(boolean ignoreEncodingErrors) throws IOException {
        return code;
    }
}
