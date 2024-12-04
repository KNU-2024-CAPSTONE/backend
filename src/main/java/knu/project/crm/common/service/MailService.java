package knu.project.crm.common.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import knu.project.crm.influx.dto.MemberInfoRestResponse;
import knu.project.crm.outflux.dto.CouponRestResponse;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {
    private final JavaMailSender javaMailSender;
    private static final String senderEmail = "test@gmail.com";

    public MailService(JavaMailSender javaMailSender){
        this.javaMailSender = javaMailSender;
    }

    public void sendMail(String email, CouponRestResponse couponRestResponse) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();

        message.setFrom(senderEmail);
        //message.setRecipients(MimeMessage.RecipientType.TO, email);
        // 이메일이 가짜이므로 테스트용으로 변경, 실제 사용 시는 위의 메소드 사용
        message.setRecipients(MimeMessage.RecipientType.TO, "dn06023@knu.ac.kr");
        message.setSubject("테스트용 쿠폰 발급");
        StringBuilder sb = new StringBuilder();
        sb.append("<h3>테스트용 쿠폰 발급입니다. 쿠폰 번호는 아래와 같습니다.</h3>")
                .append("<h1>" + couponRestResponse.code() + "</h1>")
                .append("<h3>감사합니다.</h3>");
        String body = sb.toString();

        message.setText(body, "UTF-8", "html");

        try {
            javaMailSender.send(message); // 메일 발송
        } catch (MailException e) {
            e.printStackTrace();
            throw new IllegalArgumentException("메일 발송 중 오류가 발생했습니다.");
        }
    }
}