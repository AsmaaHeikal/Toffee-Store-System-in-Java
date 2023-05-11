import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.Random;

public class OTP {

    public String generateOTP() {

        // Characters allowed in the OTP
        String allowedChars = "0123456789";

        // Random object
        Random random = new Random();

        // StringBuilder to store the OTP
        StringBuilder otp = new StringBuilder();

        // Generate random OTP
        for (int i = 0; i < 6; i++) {
            int index = random.nextInt(allowedChars.length());
            otp.append(allowedChars.charAt(index));
        }

        // Return the OTP as a string
        return otp.toString();
    }

    public void sendOTP(String userEmail, String OTP) {
        // Email details
        String host = "smtp.gmail.com";
        String port = "587";
        String username = "asmaaiheikal@gmail.com"; // replace with your email
        String password = "ddvmragbrfguvtnf"; // replace with your email password

        // User details
//        String userEmail = "asaney555@gmail.com"; // replace with user's email
//        String OTP = "123456"; // replace with generated OTP

        // Email properties
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);
        props.put("mail.smtp.ssl.protocols", "TLSv1.3");

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