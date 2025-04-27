package org.fugerit.java.turbo.unit.core.io;

import org.fugerit.java.turbo.unit.core.ex.ExampleRuntimeException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * When testing {@link ExampleReadStream} it is easy to check the successful scenario.
 *
 * But if you want to try the Exception case too, some workaround it is needed.
 *
 * In this example, we are using a custom InputStream to generate an exception.
 *
 * I used something similar in the repository :
 *
 * https://github.com/fugerit-org/fj-lib/blob/main/fj-core/src/test/java/test/org/fugerit/java/core/testhelpers/FailInputStream.java
 * https://github.com/fugerit-org/fj-lib/blob/main/fj-core/src/test/java/test/org/fugerit/java/core/cfg/store/TestConfigStore.java#L81
 *
 */
class TestExampleReadStream {

    @Test
    void testKo() throws IOException {
        ExampleReadStream example = new ExampleReadStream();
        try (InputStream is = new InputStream() {
            @Override
            public int read() throws IOException {
                if (Boolean.TRUE) {
                    throw new IOException("read failed (by scenario)");
                }
                return 0;
            }
        }) {
            Assertions.assertThrows(ExampleRuntimeException.class, () -> example.readBytes(is));
        }
    }

    @Test
    void testOk() throws IOException {
        ExampleReadStream example = new ExampleReadStream();
        String testOk = "OK";
        try (InputStream is = new ByteArrayInputStream(testOk.getBytes())) {
            // Assertions.assertThrows(ExampleRuntimeException.class, () -> example.readBytes(is));
            byte[] data = example.readBytes(is);
            Assertions.assertArrayEquals( data, testOk.getBytes() );
        }
    }

}
