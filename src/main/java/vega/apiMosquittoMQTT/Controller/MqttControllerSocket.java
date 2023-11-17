package vega.apiMosquittoMQTT.Controller;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;

import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;



import java.util.ArrayList;
import java.util.List;

@Controller
public class MqttControllerSocket {

    private MqttClient mqttClient;
    private List<String> receivedMessages = new ArrayList<>(); // Armazenar as mensagens recebidas

    //private String receivedMessages;

    private final SimpMessagingTemplate messagingTemplate;

    public MqttControllerSocket(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
        try {
            // Configurar o cliente MQTT com autenticação
            String broker = "tcp://localhost:1883";
            String clientId = MqttClient.generateClientId();

            MqttConnectOptions options = new MqttConnectOptions();
            options.setUserName("admin");
            options.setPassword("12345678".toCharArray());

            mqttClient = new MqttClient(broker, clientId, null);// adicionei persistencia null ou new MemoryPersistence()
            mqttClient.connect(options);

            // Subscrever ao tópico MQTT
            mqttClient.subscribe("consumo", (topic, message) -> {
                mensagemRecebida(new String(message.getPayload()));
            });
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

//    @GetMapping("/messages")
//    public String getMessages(Model model) {
//        model.addAttribute("messages", receivedMessages);
//        return "home";
//    }

    @GetMapping("/messages")
    public ResponseEntity<?> getMessages() {
        if (!receivedMessages.isEmpty()) {
            String latestMessage = receivedMessages.get(receivedMessages.size() - 1);
            return ResponseEntity.ok(latestMessage);
        } else {
            return ResponseEntity.ok("Aguarde.");
        }
    }


    // Método chamado quando uma mensagem é recebida
    private void mensagemRecebida(String message) {
        System.out.println("Received message: " + message);

        // Armazenar a mensagem na lista
       receivedMessages.add(message);
        //receivedMessages = message;

        // Enviar a mensagem para o cliente via WebSocket
        messagingTemplate.convertAndSend("/topic/messages", message);
    }

}
