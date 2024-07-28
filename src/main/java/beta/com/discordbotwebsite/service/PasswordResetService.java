package beta.com.discordbotwebsite.service;

import beta.com.discordbotwebsite.domain.User;
import beta.com.discordbotwebsite.mailmanager.PasswordResetToken;
import beta.com.discordbotwebsite.mailmanager.PasswordResetTokenRepository;
import beta.com.discordbotwebsite.repos.UserRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class PasswordResetService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired(required = false)
    private PasswordResetTokenRepository tokenRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private static final long EXPIRATION_MINUTES = 15;

    public boolean sendPasswordResetEmail(String email) {
        tokenRepository.deleteByEmail(email);

        String token = UUID.randomUUID().toString();

        LocalDateTime expirationTime = LocalDateTime.now().plusMinutes(EXPIRATION_MINUTES);

        PasswordResetToken passwordResetToken = new PasswordResetToken(email, token, expirationTime);

        tokenRepository.save(passwordResetToken);

        String resetLink = generateResetLink(token);

        String subject = "Password Reset Request";

        String body = "<html><body>"
                + "<h1>Reset Your Password</h1>"
                + "<p>Hello,</p>"
                + "<p>To reset your password, click the link below:</p>"
                + "<a href=\"" + resetLink + "\">Reset Your Password</a>"
                + "<p>This link will expire in " + EXPIRATION_MINUTES + " minutes.</p>"
                + "<p>If you did not request a password reset, please ignore this email.</p>"
                + "<p>Thank you,</p>"
                + "<p>The Discord Bot Team</p>"
                + "</body></html>";

        try {
            sendHtmlEmail(email, subject, body);
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
    }

    private void sendHtmlEmail(String to, String subject, String htmlBody) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(htmlBody, true);
        helper.setFrom("myemail@gmail.com");

        mailSender.send(message);
    }

    private String generateResetLink(String token) {
        return "127.0.0.1:8080/reset-password?token=" + token;
    }

    public boolean validatePasswordResetToken(String token) {
        Optional<PasswordResetToken> tokenOpt = tokenRepository.findByToken(token);

        if (tokenOpt.isPresent()) {
            PasswordResetToken passwordResetToken = tokenOpt.get();

            if (passwordResetToken.getExpirationTime().isAfter(LocalDateTime.now())) {
                return true;
            } else {
                tokenRepository.delete(passwordResetToken);
            }
        }

        return false;
    }

    public boolean resetPassword(String token, String newPassword) {
        Optional<PasswordResetToken> tokenOpt = tokenRepository.findByToken(token);

        if (tokenOpt.isPresent()) {
            PasswordResetToken passwordResetToken = tokenOpt.get();

            if (passwordResetToken.getExpirationTime().isAfter(LocalDateTime.now())) {
                Optional<User> userOpt = userRepository.findByEmail(passwordResetToken.getEmail());

                if (userOpt.isPresent()) {
                    User user = userOpt.get();


                    user.setPassword(passwordEncoder.encode(newPassword));

                    userRepository.save(user);

                    tokenRepository.delete(passwordResetToken);

                    return true;
                }
            } else {
                tokenRepository.delete(passwordResetToken);
            }
        }

        return false;
    }
}
