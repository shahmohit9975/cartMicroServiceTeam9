package com.coviam.cartMicroServiceTeam_9.service;


import com.coviam.cartMicroServiceTeam_9.dto.OtpDto;

import javax.mail.MessagingException;

public interface OtpService {
    OtpDto sendEmail(String userEmail) throws MessagingException;
}
