package com.coviam.cartMicroService.service;

import com.coviam.cartMicroService.dto.OtpDto;

import javax.mail.MessagingException;

public interface OtpService {
    OtpDto sendEmail(String userEmail) throws MessagingException;
}
