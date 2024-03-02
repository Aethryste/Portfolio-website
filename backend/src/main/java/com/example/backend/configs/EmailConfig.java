package com.example.backend.configs;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmailConfig {

    @Value("${email.host}")
    private static String host;

    @Value("${email.port}")
    private static int port;

    @Value("${email.username}")
    private static String username;

    @Value("${email.password}")
    private static String password;

    public static String getHost() {
        System.out.println(host);
        return host;
    }
    public static int getPort() {
        System.out.println(port);
        return port;
    }
    public static String getUsername() {
        System.out.println(username);
        return username;
    }
    public static String getPassword() {
        return password;
    }
}
