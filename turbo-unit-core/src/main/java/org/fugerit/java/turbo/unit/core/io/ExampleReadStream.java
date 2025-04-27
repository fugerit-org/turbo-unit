package org.fugerit.java.turbo.unit.core.io;

import org.fugerit.java.turbo.unit.core.ex.ExampleRuntimeException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * This is a simple class reading the content of an InputStream.
 *
 * If successful, a byte[] array containing the data read is returned.
 *
 * If an exception occurs, it is caught and rethrow as a RuntimeException.
 *
 */
public class ExampleReadStream {

    public byte[] readBytes( InputStream inputStream ) {
        try ( ByteArrayOutputStream buffer = new ByteArrayOutputStream() ) {
            byte[] data = new byte[1024];
            int read = inputStream.read( data );
            while ( read > 0 ) {
                buffer.write( data, 0, read );
                read = inputStream.read( data );
            }
            return buffer.toByteArray();
        } catch ( IOException e ) {
            String message = String.format( "Error reading input stream : %s", e );
            throw new ExampleRuntimeException( message, e );
        }
    }

}
