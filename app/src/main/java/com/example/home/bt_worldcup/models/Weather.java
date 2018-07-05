package com.example.home.bt_worldcup.models;

import java.io.Serializable;

/**
 * Created by Home on 6/29/2018.
 */

public class Weather implements Serializable{
    private String humidity;
    private String tempCelsius;
    private String tempFarenheit;
    private String windSpeed;
    private String description;
    //private Map<String, Object> additionalProperties = new HashMap<String, Object>();


    public Weather() {
    }

    public Weather(String humidity, String tempCelsius, String tempFarenheit, String windSpeed, String description) {
        this.humidity = humidity;
        this.tempCelsius = tempCelsius;
        this.tempFarenheit = tempFarenheit;
        this.windSpeed = windSpeed;
        this.description = description;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getTempCelsius() {
        return tempCelsius;
    }

    public void setTempCelsius(String tempCelsius) {
        this.tempCelsius = tempCelsius;
    }

    public String getTempFarenheit() {
        return tempFarenheit;
    }

    public void setTempFarenheit(String tempFarenheit) {
        this.tempFarenheit = tempFarenheit;
    }

    public String getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(String windSpeed) {
        this.windSpeed = windSpeed;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /*public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }*/
}
