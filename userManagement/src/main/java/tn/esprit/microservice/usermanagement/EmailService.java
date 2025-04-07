package tn.esprit.microservice.usermanagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.mail.internet.MimeMessage;
import jakarta.mail.MessagingException;

@Service
public class EmailService {

    private static final Logger logger = LoggerFactory.getLogger(EmailService.class);

    @Autowired
    private JavaMailSender mailSender;

    public boolean sendVerificationEmail(User user, String token) {
        try {
            logger.info("Préparation de l'email pour: " + user.getEmail());
            
            // Utiliser MimeMessage au lieu de SimpleMailMessage pour un meilleur support HTML
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
            
            String htmlMsg = "<h2>Vérification de compte Forkina</h2>" +
                    "<p>Bonjour <strong>" + user.getFirstName() + "</strong>,</p>" +
                    "<p>Veuillez cliquer sur le lien suivant pour vérifier votre compte :</p>" +
                    "<p><a href='http://localhost:8086/users/verify?token=" + token + "'>Vérifier mon compte</a></p>" +
                    "<p>Ou utilisez ce lien: <br/>" +
                    "http://localhost:8086/users/verify?token=" + token + "</p>" +
                    "<p>Cordialement,<br/>" +
                    "L'équipe Forkina</p>";
            
            helper.setTo(user.getEmail());
            helper.setSubject("Vérification de compte Forkina");
            helper.setText(htmlMsg, true); // true indique que c'est du HTML
            helper.setFrom("no-reply@forkina.com");
            
            logger.info("Tentative d'envoi d'email à: " + user.getEmail());
            mailSender.send(mimeMessage);
            logger.info("Email envoyé avec succès à: " + user.getEmail());
            
            return true;
        } catch (MessagingException e) {
            logger.error("Erreur lors de la création du message: " + e.getMessage(), e);
            return false;
        } catch (Exception e) {
            logger.error("Erreur lors de l'envoi de l'email: " + e.getMessage(), e);
            logger.error("Détails de l'erreur:", e);
            return false;
        }
    }
} 