package smarthomeapi.mqtt;

import org.eclipse.paho.client.mqttv3.*;
import org.springframework.stereotype.Component;

@Component
public class MqttPublisher {
    private static final String BROKER_URL = "tcp://85.241.132.174:1883";
    private static final String TOPIC = "commands";
    private static final String USERNAME = "mqttbroker"; // Replace with your MQTT broker username
    private static final String PASSWORD = "iot2024"; // Replace with your MQTT broker password

    public void publishCommand(String command) {
        MqttClient client;
        try {
            MqttConnectOptions options = new MqttConnectOptions();
            options.setUserName(USERNAME);
            options.setPassword(PASSWORD.toCharArray());

            client = new MqttClient(BROKER_URL, MqttClient.generateClientId());
            client.connect(options); // Pass options when connecting
            MqttMessage message = new MqttMessage(command.getBytes());
            client.publish(TOPIC, message);
            client.disconnect();
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
}
