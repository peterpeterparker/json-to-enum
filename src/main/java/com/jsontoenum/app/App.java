package com.jsontoenum.app;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public class App {

    public static void main(String[] args) {
        // Load and init the configuration library
        final Config conf = ConfigFactory.load();

        // Get the value of the JSON property
        final Cheese cheese =
                getEnumProperty(conf, "swiss.cheese", Cheese.class);

        if (Cheese.GRUYERE.equals(cheese)) {
            System.out.println(String.format("I really like %s 🧀", cheese));
        } else {
            System.out.println(String.format("%s is ok", cheese));
        }
    }

	private static <E extends Enum<E>> E getEnumProperty(final Config conf, final String key, final Class<E> myClass) {
	    // If no config loaded
	    if (conf == null) {
	        return null;
        }

	    // If the config doesn't contains the key
	    if (!conf.hasPath(key)) {
	        return null;
        }

	    // As previously, load the key value
        final String keyValue = conf.getString(key);

	    // Does the property has a value
        if (keyValue == null || keyValue.isEmpty()) {
            return null;
        }

        // Map the property to the ENUM
        return Enum.valueOf(myClass, keyValue.toUpperCase());
    }
}
