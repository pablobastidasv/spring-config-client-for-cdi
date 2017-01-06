package co.pablobastidasv;

import javax.ws.rs.client.Client;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

/**
 * Created by DELL on 06/01/2017.
 */
public class RestClient {

    private Client client;
    private WebTarget target;
    private static final String FULL_URL = ServerInfoLoader.SERVER_URL + "{appName}-{profile}.properties";

    public RestClient() {
        this.client = ClientBuilder.newClient();
        this.target = this.client.target( FULL_URL );
    }

    public Properties getValues(String appName){
        return getValues(appName, "default");
    }

    public Properties getValues(String appName, String profile){
        Objects.requireNonNull(appName);
        Objects.requireNonNull(profile);

        String info = this.target
                .resolveTemplate("appName", appName)
                .resolveTemplate("profile", profile)
                .request()
                .get(String.class);

        Properties props = new Properties();
        try {
            props.load(new ByteArrayInputStream(info.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error loading properties from the server");
        }

        return props;
    }



}
