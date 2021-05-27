package ro.ase.acs.models;

import java.util.Date;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="weather")
public class WeatherConditions {
	private String location;
	private float temperature;
	private String description;
	private Date timestamp;
	
	public String getLocation() {
		return location;
	}
	
	public void setLocation(String location) {
		this.location = location;
	}
	
	public float getTemperature() {
		return temperature;
	}
	
	public void setTemperature(float temperature) {
		this.temperature = temperature;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	@XmlAttribute
	public Date getTimestamp() {
		return timestamp;
	}
	
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
}
