package org.usfirst.frc.team4180.robot;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

public class PropertyReader {
	private final Properties properties;
	
	public PropertyReader(String propFileName) throws IOException {
		properties = new Properties();
		try(InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName)) {
			if (inputStream != null) {
				properties.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
			}
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		}
	}

	// returns the number of the port given the name of the port
	public int get(String portName) throws IllegalArgumentException {
		try {
			String portNumber = properties.getProperty(portName);
			return Integer.parseInt(portNumber);
		} catch (Exception e) {
			throw new IllegalArgumentException("Passed an invalid property", e);
		}
	}
}