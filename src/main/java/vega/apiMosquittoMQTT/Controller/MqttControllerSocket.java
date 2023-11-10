package vega.apiMosquittoMQTT.Controller;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
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

            mqttClient = new MqttClient(broker, clientId);
            mqttClient.connect(options);

            // Subscrever ao tópico MQTT
            mqttClient.subscribe("myTopic", (topic, message) -> {
                mensagemRecebida(new String(message.getPayload()));
            });
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/messages")
    public String getMessages(Model model) {
        // Aqui, você pode recuperar as mensagens armazenadas ou processadas e adicioná-las ao modelo
        // Neste exemplo, estamos utilizando a lista receivedMessages
        model.addAttribute("messages", receivedMessages);
        return "home"; // Isso pressupõe que você tem um arquivo HTML chamado home.html na pasta src/main/resources/templates
    }

    // Método chamado quando uma mensagem é recebida
    private void mensagemRecebida(String message) {
        System.out.println("Received message: " + message);

        // Armazenar a mensagem na lista
        receivedMessages.add(message);

        // Enviar a mensagem para o cliente via WebSocket
        messagingTemplate.convertAndSend("/topic/messages", message);
    }
}
