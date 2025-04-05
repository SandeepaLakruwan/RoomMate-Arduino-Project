#include <WiFi.h>
#include <HTTPClient.h>
#include <DHT.h>

#define DHTPIN 15      // Pin connected to the DHT22 data pin
#define DHTTYPE DHT22  // DHT 22 (AM2302) sensor
DHT dht(DHTPIN, DHTTYPE);

#define MQ135_PIN 34   // Pin connected to the MQ-135 analog output
#define SOUND_PIN 35   // Pin connected to the sound sensor analog output

// Replace with your Wi-Fi network credentials
const char* ssid = "SVITIN-C697";
const char* password = "10251404";

// Replace with your server's URL
const char* serverName = "https://5481-175-157-253-170.ngrok-free.app/RoomMate/GetData";

void setup() {
  Serial.begin(115200);

  // Initialize sensors
  dht.begin();

  // Connect to Wi-Fi
  WiFi.begin(ssid, password);
  while (WiFi.status() != WL_CONNECTED) {
    delay(1000);
    Serial.println("Connecting to WiFi...");
  }
  Serial.println("Connected to WiFi");
}

void loop() {
  // Check Wi-Fi connection status
  if (WiFi.status() == WL_CONNECTED) {
    HTTPClient http;

    // Measure temperature and humidity
    float temperature = dht.readTemperature();
    float humidity = dht.readHumidity();

    // Read air quality from MQ-135
    int mq135Value = analogRead(MQ135_PIN);
    float airQuality = (mq135Value / 4095.0) * 100;  // Example scale for air quality in ppm

    // Read noise level from sound module
    int noiseValue = analogRead(SOUND_PIN);
    float noiseLevel = (noiseValue / 4095.0) * 100;  // Scale to percentage or dB as needed

    // Check if any readings failed
    if (isnan(temperature) || isnan(humidity)) {
      Serial.println("Failed to read from DHT sensor!");
      temperature = 0;
      humidity = 0;
      delay(1000);
    }

    // Create HTTP GET request URL with parameters
    String url = String(serverName) + "?temperature=" + String(temperature, 1) +
                 "&humidity=" + String(humidity, 1) +
                 "&air_quality=" + String(airQuality, 1) +
                 "&noise=" + String(noiseLevel, 1);

    http.begin(url);  // Specify URL
    int httpResponseCode = http.GET();  // Send HTTP GET request

    // Check the response code
    if (httpResponseCode > 0) {
      String payload = http.getString();
      Serial.println("Response code: " + String(httpResponseCode));
      Serial.println("Server response: " + payload);
    } else {
      Serial.println("Error on sending GET request: " + String(httpResponseCode));
    }

    // End the HTTP connection
    http.end();
  } else {
    Serial.println("Disconnected from WiFi");
  }

  // Wait for 10 seconds before the next reading
  delay(10000);
}
