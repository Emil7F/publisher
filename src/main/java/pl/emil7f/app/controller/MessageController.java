package pl.emil7f.app.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import pl.emil7f.app.model.Notification;
import pl.emil7f.app.model.Student;
import pl.emil7f.app.service.NotificationService;

@RestController
public class MessageController {

    private RestTemplate restTemplate;
    private NotificationService notificationService;
    private RabbitTemplate rabbitTemplate;

    public MessageController(RestTemplate restTemplate,
                             NotificationService notificationService,
                             RabbitTemplate rabbitTemplate) {
        this.restTemplate = restTemplate;
        this.notificationService = notificationService;
        this.rabbitTemplate = rabbitTemplate;
    }

    @GetMapping("/notification/{id}")
    public Notification getStudent(@PathVariable Long id) {
        Student student = restTemplate.exchange(
                "http://localhost:8085/students/" + id,
                HttpMethod.GET,
                HttpEntity.EMPTY,
                Student.class)
                .getBody();
        Notification notification = notificationService.studentMapper(student);
        rabbitTemplate.convertAndSend("myQueue", notification);
        return notification;
    }
}
