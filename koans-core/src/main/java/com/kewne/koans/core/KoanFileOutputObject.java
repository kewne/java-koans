package com.kewne.koans.core;

import javax.lang.model.element.Modifier;
import javax.lang.model.element.NestingKind;
import javax.tools.JavaFileObject;
import java.io.*;
import java.net.URI;

public class KoanFileOutputObject implements JavaFileObject {

    private final String qualifiedName;
    private ByteArrayOutputStream output;

    public KoanFileOutputObject(String qualifiedName) {
        this.qualifiedName = qualifiedName;
    }

    @Override
    public Kind getKind() {
        return Kind.CLASS;
    }

    @Override
    public boolean isNameCompatible(String simpleName, Kind kind) {
        if (!getKind().equals(kind)) { return false; }
        if (!qualifiedName.endsWith(simpleName)) { return false; }

        int index = qualifiedName.lastIndexOf(simpleName);
        if (index == -1) { throw new IllegalStateException("Name matched qualified name ending but failed to find index!"); }
        return (index == 0) || (qualifiedName.charAt(index - 1) == '.');
    }

    @Override
    public NestingKind getNestingKind() {
        return null;
    }

    @Override
    public Modifier getAccessLevel() {
        return Modifier.PUBLIC;
    }

    @Override
    public URI toUri() {
        return null;
    }

    @Override
    public String getName() {
        return qualifiedName;
    }

    @Override
    public InputStream openInputStream() throws IOException {
        throw new UnsupportedOperationException("Object is output only");
    }

    @Override
    public OutputStream openOutputStream() throws IOException {
        return output = new ByteArrayOutputStream();
    }

    @Override
    public Reader openReader(boolean ignoreEncodingErrors) throws IOException {
        throw new UnsupportedOperationException("Object is output only");
    }

    @Override
    public CharSequence getCharContent(boolean ignoreEncodingErrors) throws IOException {
        return null;
    }

    @Override
    public Writer openWriter() throws IOException {
        return null;
    }

    @Override
    public long getLastModified() {
        return 0;
    }

    @Override
    public boolean delete() {
        return false;
    }

    public byte[] getWrittenBytes() {
        return output.toByteArray();
    }
}
