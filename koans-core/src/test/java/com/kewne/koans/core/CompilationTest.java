package com.kewne.koans.core;

import org.junit.Before;
import org.junit.Test;

public final class CompilationTest {

    private KoanFactory factory;
    @Before
    public void setUp() {
        factory = new KoanFactory();
    }

    @Test
    public void testBasicCompilationSucceeds() {
        InMemoryJavaSourceObject source = new InMemoryJavaSourceObject(
                "com.kewne.koans.TestClass",
                "package com.kewne.koans;public class TestClass {" +
                        "}"
        );
        factory.compile(source);
    }

    @Test(expected = KoanCompilationException.class)
    public void testDetectsCompilationFailureDueToWrongFileName() {
        InMemoryJavaSourceObject source = new InMemoryJavaSourceObject(
                "com.kewne.koans.WrongClass",
                "package com.kewne.koans;public class TestClass {" +
                        "}"
        );
        factory.compile(source);
    }

    @Test
    public void testCompileInnerClass() {
        InMemoryJavaSourceObject source = new InMemoryJavaSourceObject(
                "com.kewne.koans.ClassWithInnerClass",
                "package com.kewne.koans; public class ClassWithInnerClass {" +
                        "private static final class TheInnerClass { }" +
                        "}"
        );
        factory.compile(source);
    }
}
