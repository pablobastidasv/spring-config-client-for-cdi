package co.pablobastidasv;

import org.junit.Before;
import org.junit.Test;

import java.util.Properties;

import static org.junit.Assert.*;

/**
 * Created by DELL on 06/01/2017.
 */
public class RestClientTest {

    RestClient restClient;

    @Before
    public void setUp(){
        restClient = new RestClient();
    }

    @Test
    public void getValues() throws Exception {
        Properties values = restClient.getValues("person-entity");

        System.out.println(values);
        System.out.println();
        System.out.println(values.get("eureka.client.serviceUrl.defaultZone"));
    }


}