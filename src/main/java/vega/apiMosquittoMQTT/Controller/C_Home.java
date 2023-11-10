package vega.apiMosquittoMQTT.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import vega.apiMosquittoMQTT.MqttBeans;


@Controller
public class C_Home {

    @Autowired
    MqttBeans mqttBeans;

    @GetMapping("/")
    public String getHome(Model model) {
        return "home";
    }


}
