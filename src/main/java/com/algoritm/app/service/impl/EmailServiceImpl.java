package com.algoritm.app.service.impl;

import com.algoritm.app.entity.algoritm.AlgoritmResult;
import com.algoritm.app.entity.algoritm.Result;
import com.algoritm.app.service.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.List;

@Slf4j
@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String usernameThatSends;

    @Override
    public void sendEmail(String emailTo, List<AlgoritmResult> algoritmResults) {
        String messageBody = getMessageBody(algoritmResults);

        try{
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");

            mimeMessage.setText(messageBody);
            helper.setTo(emailTo);
            helper.setSubject("Algoritm performer result");
            helper.setFrom(usernameThatSends);

            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    private static String getMessageBody(List<AlgoritmResult> algoritmResultList) {
        StringBuilder result = new StringBuilder();
        int count = 1;

        for (AlgoritmResult algoritmResult : algoritmResultList) {
            result.append(count + ". ");
            result.append(algoritmResult.getAlgoritmType() + "\n");
            result.append("InFile : " + algoritmResult.getInFile() + "\n");
            result.append("Reult: " + algoritmResult.getResult() + "\n");

            if (algoritmResult.getResult() == Result.SUCCESS) {
                result.append("Values quantity: " + algoritmResult.getValuesQuantity() + "\n");
                result.append("OutFile: " + algoritmResult.getOutFile() + "\n");
                result.append("Duration: " + algoritmResult.getDuration()  + " seconds" + "\n");
            }
            result.append("\n");
        }

        return result.toString();
    }
}
