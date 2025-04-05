/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.SensorData;

/**
 *
 * @author sande
 */
@WebServlet(name = "ParseData", urlPatterns = {"/ParseData"})
public class ParseData extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        SensorData sensorData;
        // Retrieve the SensorData object from the ServletContext
        if (getServletContext().getAttribute("sensorData") != null) {
            sensorData = (SensorData) getServletContext().getAttribute("sensorData");
        } else {
            sensorData = new SensorData();

            sensorData.setTemperatureParam("0");
            sensorData.setHumidityParam("0");
            sensorData.setAirQualityParam("0");
            sensorData.setNoiseParam("0");

            // Store the SensorData object in the ServletContext
            getServletContext().setAttribute("sensorData", sensorData);
        }

        Gson gson = new Gson();
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("status", false);

        // Temperature
        if (sensorData.getTemperatureParam() == null) {
            jsonObject.addProperty("temperature_status", 0);
            jsonObject.addProperty("temp_warning", "Temperature Sensor Error.");
        } else {
            float temperature = Float.parseFloat(sensorData.getTemperatureParam());
            jsonObject.addProperty("temperature_value", temperature);
            if (temperature == 0) {
                jsonObject.addProperty("temperature_status", 0);
                jsonObject.addProperty("temp_warning", "Temperature Sensor Error.");
            } else if (temperature > 0 && temperature <= 18) {
                jsonObject.addProperty("temperature_status", 4);
                jsonObject.addProperty("temp_warning", "Slightly very low temperature.");
            } else if (temperature >= 18 && temperature <= 32) {
                jsonObject.addProperty("temperature_status", 1);
                jsonObject.addProperty("temp_warning", "Temperature is within normal range.");
            } else if (temperature > 32 && temperature <= 45) {
                jsonObject.addProperty("temperature_status", 2);
                jsonObject.addProperty("temp_warning", "Slightly high temperature.");
            } else {
                jsonObject.addProperty("temperature_status", 3);
                jsonObject.addProperty("temp_warning", "Temperature outside very low or very high.");
            }
        }

        // Humidity
        if (sensorData.getHumidityParam() == null) {
            jsonObject.addProperty("humidity_status", 0);
            jsonObject.addProperty("hum_warning", "Humidity Sensor Error.");
        } else {
            float humidity = Float.parseFloat(sensorData.getHumidityParam());
            jsonObject.addProperty("humidity_value", humidity);
            if (humidity == 0) {
                jsonObject.addProperty("humidity_status", 0);
                jsonObject.addProperty("hum_warning", "Humidity Sensor Error.");
            } else if (humidity > 0 && humidity <= 30) {
                jsonObject.addProperty("humidity_status", 4);
                jsonObject.addProperty("hum_warning", "Humidity is low range.");
            } else if (humidity >= 30 && humidity <= 50) {
                jsonObject.addProperty("humidity_status", 1);
                jsonObject.addProperty("hum_warning", "Humidity is within normal range.");
            } else if (humidity > 50 && humidity <= 60) {
                jsonObject.addProperty("humidity_status", 2);
                jsonObject.addProperty("hum_warning", "Slightly high humidity.");
            } else {
                jsonObject.addProperty("humidity_status", 3);
                jsonObject.addProperty("hum_warning", "Humidity outside very high range.");
            }
        }

        // Air Quality
        if (sensorData.getAirQualityParam() == null) {
            jsonObject.addProperty("air_quality_status", 0);
            jsonObject.addProperty("air_quality_warning", "Air quality Sensor Error.");
        } else {
            float airQuality = Float.parseFloat(sensorData.getAirQualityParam());
            jsonObject.addProperty("air_quality_value", airQuality);
            if (airQuality == 0) {
                jsonObject.addProperty("air_quality_status", 0);
                jsonObject.addProperty("air_quality_warning", "Air quality Sensor Error.");
            } else if (airQuality <= 100) {
                jsonObject.addProperty("air_quality_status", 1);
                jsonObject.addProperty("air_quality_warning", "Air quality is good.");
            } else if (airQuality <= 200) {
                jsonObject.addProperty("air_quality_status", 2);
                jsonObject.addProperty("air_quality_warning", "Poor air quality.");
            } else {
                jsonObject.addProperty("air_quality_status", 3);
                jsonObject.addProperty("air_quality_warning", "Hazardous air quality.");
            }
        }

        // Noise
        if (sensorData.getNoiseParam() == null) {
            jsonObject.addProperty("noise_status", 0);
            jsonObject.addProperty("noise_warning", "Noise Sensor Error.");
        } else {
            float noiseLevel = Float.parseFloat(sensorData.getNoiseParam());
            jsonObject.addProperty("noise_value", noiseLevel);
            if (noiseLevel == 0) {
                jsonObject.addProperty("noise_status", 0);
                jsonObject.addProperty("noise_warning", "Noise Sensor Error.");
            } else if (noiseLevel <= 60) {
                jsonObject.addProperty("noise_status", 1);
                jsonObject.addProperty("noise_warning", "Noise level is normal.");
            } else if (noiseLevel <= 80) {
                jsonObject.addProperty("noise_status", 2);
                jsonObject.addProperty("noise_warning", "Loud environment detected.");
            } else {
                jsonObject.addProperty("noise_status", 3);
                jsonObject.addProperty("noise_warning", "High noise level detected.");
            }
        }
        jsonObject.addProperty("status", true);

        response.setContentType("application/json");
        response.getWriter().write(gson.toJson(jsonObject));
    }
}
