package com.kewne.koans.core;

import java.security.ProtectionDomain;

public class KoanClassLoader extends ClassLoader {

    private static final ProtectionDomain NO_PERMISSIONS_DOMAIN = new ProtectionDomain(null, null);

    public KoanClassLoader(ClassLoader parent) {
        super(parent);
    }

    public Class<?> loadKoan(KoanFileOutputObject obj) {
        byte[] classBytes = obj.getWrittenBytes();
        return defineClass(null, classBytes, 0, classBytes.length, NO_PERMISSIONS_DOMAIN);
    }
}
