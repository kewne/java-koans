package com.kewne.koans.core;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.ToolProvider;
import java.io.StringWriter;
import java.util.Arrays;

public final class KoanFactory {

    private final JavaCompiler compiler;
    private final KoanFileManager fileManager;

    public KoanFactory() {
        compiler = ToolProvider.getSystemJavaCompiler();
        fileManager = new KoanFileManager(compiler.getStandardFileManager(null, null, null));
    }

    public KoanFileOutputObject compile(JavaFileObject object) {
        StringWriter writer = new StringWriter();
        JavaCompiler.CompilationTask task = compiler.getTask(writer, fileManager, null, null, null, Arrays.asList(object));
        if (task.call()) {
            return fileManager.getKoanOutputFile(object.getName());
        } else {
            throw new KoanCompilationException("Could not compile the class:\n" + writer.toString());
        }
    }

    public Class<?> compileAndLoad(JavaFileObject object) {
        KoanFileOutputObject koanObject = compile(object);
        return new KoanClassLoader(ClassLoader.getSystemClassLoader())
                .loadKoan(koanObject);
    }
}
