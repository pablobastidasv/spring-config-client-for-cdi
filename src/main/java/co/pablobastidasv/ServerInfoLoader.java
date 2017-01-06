package co.pablobastidasv;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.Properties;

/**
 * Created by DELL on 06/01/2017.
 */
public class ServerInfoLoader {

    private static final String DEFAULT_PATH_FILE = "config.properties";
    private static final String DEFAULT_SERVER_URL = "http://localhost:8888/";
    private static final String SERVER_URL_PROPERTY = "config.server.url";
    private static final String APP_NAME_PROPERTY = "application.name";
    private static final String ENVIRONMENT_PROPERTY = "co.pablobastidas.config.file";
    public static final String SERVER_URL;
    public static final String APP_NAME;

    static {
        Optional<String> serverUrlOptional= Optional.empty();
        Optional<String> appNameOptional= Optional.empty();

        try {
            Optional<String> configPath = Optional.ofNullable(System.getenv().get(ENVIRONMENT_PROPERTY));

            Path configFilePath = Paths.get(configPath.orElse(DEFAULT_PATH_FILE));

            if (Files.exists(configFilePath)) {
                Properties props = getProperties(configFilePath);
                serverUrlOptional = Optional.ofNullable(props.getProperty(SERVER_URL_PROPERTY));
                appNameOptional = Optional.ofNullable(props.getProperty(APP_NAME_PROPERTY));
            } else {
                System.out.println("Configuration file does not exists.");
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error when load the file configuration.");
        }

        SERVER_URL = serverUrlOptional.orElse(DEFAULT_SERVER_URL);
        APP_NAME = appNameOptional.orElse(DEFAULT_SERVER_URL);
    }

    private static Properties getProperties(Path path) throws IOException {
        try(InputStream in = Files.newInputStream(path)){
            Properties props = new Properties();
            props.load(in);

            return props;
        }
    }

}
