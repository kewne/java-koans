package com.kewne.koans.core;

import javax.tools.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class KoanFileManager extends ForwardingJavaFileManager<JavaFileManager> {

    private final Map<String, KoanFileOutputObject> classObjectMap;

    public KoanFileManager(JavaFileManager delegate) {
        super(delegate);
        classObjectMap = new HashMap<>();
    }

    @Override
    public boolean isSameFile(FileObject a, FileObject b) {
        return a.getName().equals(b.getName());
    }

    @Override
    public JavaFileObject getJavaFileForOutput(JavaFileManager.Location location, String className, JavaFileObject.Kind kind, FileObject sibling) throws IOException {
        if (StandardLocation.CLASS_OUTPUT.getName().equals(location.getName())) {
            KoanFileOutputObject outObject = new KoanFileOutputObject(className);
            classObjectMap.put(className, outObject);
            return outObject;
        }
        return super.getJavaFileForOutput(location, className, kind, sibling);
    }

    public KoanFileOutputObject getKoanOutputFile(String name) {
        return classObjectMap.get(name);
    }
}
