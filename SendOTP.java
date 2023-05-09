import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class SendOTP {

    public static void main(String[] args) {
        // Email details
        String host = "smtp.gmail.com";
        String port = "587";
        String username = "asaney555@gmail.com"; // replace with your email
        String password = "vkp8get3cvm0FJT!uwt"; // replace with your email password

        // User details
        String userEmail = "asmaaiheikal@gmail.com"; // replace with user's email
        String OTP = "123456"; // replace with generated OTP

        // Email properties
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);
        props.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");

        // Create session with authentication
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            // Create message
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(userEmail));
            message.setSubject("OTP Verification");
            message.setText("Your OTP is " + OTP);

            // Send message
            Transport.send(message);

            System.out.println("OTP sent successfully to " + userEmail);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
