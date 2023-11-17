package vega.apiMosquittoMQTT.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;


import org.springframework.web.bind.annotation.*;


import com.google.gson.Gson;
import com.google.gson.JsonObject;

import vega.apiMosquittoMQTT.MqttGateway;


@RestController
public class C_Mqtt {

    @Autowired
    MqttGateway mqttGateway;

    // POST PARA JSON APPLICATION/JSON
//    @PostMapping("/sendMessage")
//    public ResponseEntity<?> publish(@RequestBody String mqttMessage) {
//
//        try {
//            JsonObject convertObject = new Gson().fromJson(mqttMessage, JsonObject.class);
//            // message correnponde ao comando e topic corresponde ao id_dispositivo
//            mqttGateway.senToMqtt(convertObject.get("comando").toString(), convertObject.get("coderele").toString());
//            return ResponseEntity.ok("Success");
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            return ResponseEntity.ok("fail");
//        }
//    }


    // POST PARA STRING TEXT/PLAIN
    @PostMapping("/sendMessage")
    public ResponseEntity<?> publish(@RequestBody String mqttMessage) {
        try {
            mqttGateway.senToMqtt(mqttMessage, "myTopic");  // O segundo parâmetro é null, ajuste conforme necessário
            return ResponseEntity.ok("Success");
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.ok("fail");
        }
    }

}
