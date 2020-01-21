package com.coviam.cartMicroService.service.impl;

import com.coviam.cartMicroService.dto.EmailOrderDetailsDTO;
import com.coviam.cartMicroService.dto.OtpDto;
import com.coviam.cartMicroService.service.OtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;

@Service
public class OtpServiceImpl implements OtpService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public OtpDto sendEmail(String userEmail) throws MessagingException {

        int randomPIN = (int) (Math.random() * 9000) + 1000;
        System.out.println("OTP :" + randomPIN);

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
        helper.setTo(userEmail);
        helper.setSubject("Your OTP");
        String orderMsg = String.valueOf(randomPIN);

        helper.setText("<b> Your OTP IS : " + orderMsg + "</b>", true);
        javaMailSender.send(mimeMessage);
        OtpDto otpDto = new OtpDto();
        otpDto.setOtp(randomPIN);
        return otpDto;
    }
}
