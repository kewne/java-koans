package com.kewne.koans.core;

import com.kewne.koans.core.InMemoryJavaSourceObject;
import com.kewne.koans.core.KoanFactory;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public final class ClassloadingTest {

    private KoanFactory factory;

    @Before
    public void setUp() {
        factory = new KoanFactory();
    }

    @Test
    public void testClassCorrectlyLoaded() {
        InMemoryJavaSourceObject source = new InMemoryJavaSourceObject(
                "com.kewne.com.kewne.koans.test.TestClass",
                "package com.kewne.com.kewne.koans.test;public final class TestClass {}"
        );
        Class<?> theClass = factory.compileAndLoad(source);
        assertTrue(theClass.getName().equals("com.kewne.com.kewne.koans.test.TestClass"));
    }
}
