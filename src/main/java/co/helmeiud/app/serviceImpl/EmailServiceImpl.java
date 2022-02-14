package co.helmeiud.app.serviceImpl;

import co.helmeiud.app.service.ServiceEmail;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
@AllArgsConstructor
public class EmailServiceImpl implements ServiceEmail {

	private static final Logger log = LoggerFactory.getLogger(EmailServiceImpl.class);


	private JavaMailSender sender;
	
	@Override
	public boolean sendEmail(String message, String to, String subject) {
		log.info("Message {}", message);
		return sendMailTool(message, to, subject);
	}
	
	private boolean sendMailTool(String message, String to, String subject) {
		boolean sent = false;
		MimeMessage msg = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(msg);
		try {
			helper.setFrom("noreply@iudigital.edu.co");
			helper.setTo(to);
			helper.setText(message, true);
			helper.setSubject(subject);
			sender.send(msg);
			sent = true;
		} catch (MessagingException e) {
			log.error("Error {}", e);
		}
		return sent;
	}
}
