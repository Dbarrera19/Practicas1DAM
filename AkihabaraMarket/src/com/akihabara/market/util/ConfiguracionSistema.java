package com.akihabara.market.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfiguracionSistema {
    private static Properties props = new Properties();

    static {
        try {
            props.load(new FileInputStream("config.properties"));
        } catch (IOException e) {
            System.out.println("No se pudo cargar el archivo de configuración.");
        }
    }

    public static String get(String clave) {
        return props.getProperty(clave);
    }
}
