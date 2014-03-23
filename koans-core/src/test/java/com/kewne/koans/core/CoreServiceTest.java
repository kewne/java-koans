package com.kewne.koans.core;

import com.kewne.koans.api.Koan;
import com.kewne.koans.api.Response;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;

import static org.junit.Assert.assertTrue;

public class CoreServiceTest {

    private CoreService coreService;
    private Koan.Id id = new Koan.Id() { };

    @Before
    public void setUp() throws Exception {
        coreService = new CoreService(
                Collections.<Koan>singletonList(
                        new IndexedKoan(
                                id,
                                "com.kewne.test.TestClass",
                                "package com.kewne.test; %s",
                                1
                        )
                )
        );
    }

    @Test
    public void testResponse() {
        assertTrue(
                coreService.testResponse(
                        new Response() {
                            @Override
                            public Koan.Id getId() {
                                return id;
                            }

                            @Override
                            public String getContent() {
                                return "public class TestClass {}";
                            }
                        }
                ));
    }
}
