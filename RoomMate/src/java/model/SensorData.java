/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;

/**
 *
 * @author sande
 */
public class SensorData implements Serializable {

    private String temperatureParam = null;
    private String humidityParam = null;
    private String airQualityParam = null;
    private String noiseParam = null;

    public SensorData() {
    }

    public String getTemperatureParam() {
        return temperatureParam;
    }

    public void setTemperatureParam(String temperatureParam) {
        this.temperatureParam = temperatureParam;
    }

    public String getHumidityParam() {
        return humidityParam;
    }

    public void setHumidityParam(String humidityParam) {
        this.humidityParam = humidityParam;
    }

    public String getAirQualityParam() {
        return airQualityParam;
    }

    public void setAirQualityParam(String airQualityParam) {
        this.airQualityParam = airQualityParam;
    }

    public String getNoiseParam() {
        return noiseParam;
    }

    public void setNoiseParam(String noiseParam) {
        this.noiseParam = noiseParam;
    }

}
