package aiu.edu.kg.FaceRecognation.jobs;

import aiu.edu.kg.FaceRecognation.entity.Request;
import aiu.edu.kg.FaceRecognation.enums.StageStatus;
import aiu.edu.kg.FaceRecognation.service.EmailService;
import aiu.edu.kg.FaceRecognation.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SendNotification {

    @Autowired
    private RequestService requestService;

    @Autowired
    private EmailService emailService;

    @Scheduled(cron = "0 * * ? * *")
    public void sendNotification(){
        List<Request> requests = requestService.findAllByStatusAndSent(StageStatus.FINISHED, 0);
        for (Request request : requests){
            SimpleMailMessage registrationEmail = new SimpleMailMessage();
            registrationEmail.setTo(request.getUser().getEmail());
            registrationEmail.setSubject("Face recognition result");
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Dear " + request.getUser().getFullName()).append("\n").append("Your request ").append(request.getTitle())
                    .append(" is ready, the result you may see on site").append("\n\n").append("Best regards,\n").append("Face recognition system");
            registrationEmail.setText(stringBuffer.toString());
            registrationEmail.setFrom("noreply@domain.com");
            emailService.sendEmail(registrationEmail);
            request.setSent(1);
            requestService.justSave(request);
        }
    }

}
