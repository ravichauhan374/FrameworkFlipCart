package dataProvider;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;




public class ConfigFileReader {
	 
	 private Properties properties;
	 private final String propertyFilePath= "/framework/src/main/resources/config/Configuation.properties";
	 Logger log = Logger.getLogger(ConfigFileReader.class);
	 
	 public ConfigFileReader(){
	 BufferedReader reader;
	 try {
	 reader = new BufferedReader(new FileReader(propertyFilePath));
	 properties = new Properties();
	 try {
	 properties.load(reader);
	 reader.close();
	 } catch (IOException e) {
	 e.printStackTrace();
	 }
	 } catch (FileNotFoundException e) {
	 e.printStackTrace();
	 throw new RuntimeException("Configuration.properties not found at " + propertyFilePath);
	 } 
	 }
	 
	 public String getDriverPath(String browser){
	 String driverPath=null;
	 switch(browser){
	 case "chrome": driverPath = properties.getProperty("chrome");
	 				break;
	 				
	 case "fireFox": driverPath = properties.getProperty("fireFox");
					break;
					
	 case "internetExplorer": driverPath = properties.getProperty("internetExplorer");
					break;
	 }
	 if(driverPath!= null) {
		 return driverPath;
	 }
	 else throw new RuntimeException("driverPath not specified in the Configuration.properties file."); 
	 }
	 
	 public long getImplicitlyWait() { 
	 String implicitlyWait = properties.getProperty("implicitlyWait");
	 if(implicitlyWait != null) return Long.parseLong(implicitlyWait);
	 else throw new RuntimeException("implicitlyWait not specified in the Configuration.properties file."); 
	 }
	 
	 public String getApplicationUrl() {
	 String url = properties.getProperty("url");
	 if(url != null) {
		 return url;
	 }
	 else throw new RuntimeException("url not specified in the Configuration.properties file.");
	 }
}
