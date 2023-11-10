package vega.apiMosquittoMQTT.Controller;


import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessagingException;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import com.google.gson.Gson;
import com.google.gson.JsonObject;

import vega.apiMosquittoMQTT.MqttGateway;



import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@RestController
public class C_Mqtt {

    @Autowired
    MqttGateway mqttGateway;


    @PostMapping("/sendMessage")
    public ResponseEntity<?> publish(@RequestBody String mqttMessage) {

        try {
            JsonObject convertObject = new Gson().fromJson(mqttMessage, JsonObject.class);
            // message correnponde ao comando e topic corresponde ao id_dispositivo
            mqttGateway.senToMqtt(convertObject.get("comando").toString(), convertObject.get("coderele").toString());
            return ResponseEntity.ok("Success");
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.ok("fail");
        }
    }


}
