package ca.retrylife.libics.files;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Wrapper for something remote
 */
public class RemoteResource {
    private URL resource;

    public RemoteResource(String url) throws MalformedURLException{
        this(new URL(url));
    }

    public RemoteResource(URL location) {
        this.resource = location;
    }

    public BufferedReader getBuffer() throws IOException {
        return new BufferedReader(new InputStreamReader(resource.openStream()));
    }

    public String getString() throws IOException {
        return (new BufferedReader(new InputStreamReader(resource.openStream()))).toString();

    }
}