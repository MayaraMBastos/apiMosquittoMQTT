package vega.apiMosquittoMQTT;

import org.eclipse.paho.client.mqttv3.MqttAsyncClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.handler.annotation.Header;

@MessagingGateway(defaultRequestChannel = "mqttOutboundChannel")
public interface MqttGateway {

    void senToMqtt(String data, @Header(MqttHeaders.TOPIC) String topic);

    void receiveFromMqtt(String data, @Header(MqttHeaders.TOPIC) String topic);
}
