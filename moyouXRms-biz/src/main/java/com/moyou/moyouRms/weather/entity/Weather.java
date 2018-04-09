package com.moyou.moyouRms.weather.entity;

import java.io.Serializable;



import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;



@Component
public class Weather implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3894504704863016735L;
	@Value("#{configProperties['AILI_WEATHER_URL']}")
	private String AILI_WEATHER_URL;

	private String city;

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
}
