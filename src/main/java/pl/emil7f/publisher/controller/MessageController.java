package pl.emil7f.publisher.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.*;
import pl.emil7f.publisher.model.Notification;

@RestController
public class MessageController {

    private final RabbitTemplate rabbitTemplate;

    public MessageController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @PostMapping("/notification")
    public String sendNotification(@RequestBody Notification notification) {
        rabbitTemplate.convertAndSend("myQueue", notification);
        return "Notification send";
    }
}
