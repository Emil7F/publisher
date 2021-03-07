package pl.emil7f.app.service;

import org.springframework.stereotype.Service;
import pl.emil7f.app.model.Notification;
import pl.emil7f.app.model.Student;

@Service
public class NotificationService {
    private Student student;

    public NotificationService(Student student) {
        this.student = student;
    }

    public Notification studentMapper(Student student) {
        Notification notification = new Notification();
        notification.setEmail(student.getEmail());
        notification.setTitle("Hello " + student.getFirstName());
        notification.setBody("It's nice that you are with us " + student.getFirstName() + " " + student.getLastName());
        return notification;
    }
}
