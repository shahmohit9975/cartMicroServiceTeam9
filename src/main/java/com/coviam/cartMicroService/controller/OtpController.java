package com.coviam.cartMicroService.controller;

import com.coviam.cartMicroService.dto.EmailDTO;
import com.coviam.cartMicroService.dto.OtpDto;

import com.coviam.cartMicroService.service.OtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/otp")
public class OtpController {
    @Autowired
    OtpService otpService;

    @PostMapping(path = "/get")
    public ResponseEntity<OtpDto> getOtp(@RequestBody EmailDTO emailDTO) {
        OtpDto otpDto = null;
        try {
            otpDto = otpService.sendEmail(emailDTO.getUserEmail());
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<OtpDto>(otpDto, HttpStatus.OK);

    }
}
