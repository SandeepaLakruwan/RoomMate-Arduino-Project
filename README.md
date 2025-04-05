# RoomMate - Smart Room Environment Monitor (Arduino + Java Backend)

RoomMate is an IoT-based smart room monitoring system that uses Arduino sensors and a Java backend to monitor **temperature**, **humidity**, **noise levels**, and **air quality** in real-time.

This repository contains:
- Arduino code for ESP32 and sensors (DHT22, MQ-135, Sound Sensor)
- Java Servlet backend to receive sensor data and provide status messages

📺 **Watch the Full Demo on YouTube:**  
👉 [Demo Video](https://youtu.be/MCi49OMHJGY)

## 🚀 Features
- Real-time sensor data collection via ESP32
- Java backend API to serve sensor status and warning messages
- Easily expandable for additional sensors or storage integration

## 🧰 Hardware Used
- ESP32 WiFi module  
- DHT22 (Temperature & Humidity Sensor)  
- MQ-135 (Air Quality Sensor)  
- Sound Sensor  
- USB power source

## 🛠 Technologies
- Arduino (C/C++)  
- Java Servlets  
- HTTP communication via ESP32

## 📡 How It Works
1. ESP32 reads sensor data and sends HTTP requests to the Java servlet backend.
2. Backend processes sensor values and returns room condition + warnings.
3. React Native app (in [this repo](https://github.com/SandeepaLakruwan/RoomMate-React-Native-Mobile-Application-for-Arduino-Project.git)) consumes the backend API.

---
