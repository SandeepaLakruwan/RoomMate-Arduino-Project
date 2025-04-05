/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

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
@WebServlet(name = "GetData", urlPatterns = {"/GetData"})
public class GetData extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        SensorData sensorData = new SensorData();

        String temperatureParam = request.getParameter("temperature");
        String humidityParam = request.getParameter("humidity");
        String airQualityParam = request.getParameter("air_quality");
        String noiseParam = request.getParameter("noise");
        
        sensorData.setTemperatureParam(temperatureParam);
        sensorData.setHumidityParam(humidityParam);
        sensorData.setAirQualityParam(airQualityParam);
        sensorData.setNoiseParam(noiseParam);
        
        // Store the SensorData object in the ServletContext
        getServletContext().setAttribute("sensorData", sensorData);

        response.getWriter().write("Sensor data stored in ServletContext.");

    }

}
