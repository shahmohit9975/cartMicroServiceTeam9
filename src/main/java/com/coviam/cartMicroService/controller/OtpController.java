package com.coviam.cartMicroService.controller;

import com.coviam.cartMicroService.dto.OtpDto;

import com.coviam.cartMicroService.service.OtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;

@CrossOrigin
@RestController
@RequestMapping("/otp")
public class OtpController {
    @Autowired
    OtpService otpService;

    @GetMapping(path = "/get/{userEmail}")
    public ResponseEntity<OtpDto> getOtp(@PathVariable String userEmail) {
        OtpDto otpDto = null;
        try {
            otpDto = otpService.sendEmail(userEmail);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<OtpDto>(otpDto, HttpStatus.OK);

    }
}
