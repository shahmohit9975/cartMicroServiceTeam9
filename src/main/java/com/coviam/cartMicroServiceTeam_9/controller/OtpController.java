package com.coviam.cartMicroServiceTeam_9.controller;


import com.coviam.cartMicroServiceTeam_9.dto.EmailDTO;
import com.coviam.cartMicroServiceTeam_9.dto.OtpDto;
import com.coviam.cartMicroServiceTeam_9.service.OtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/otp")
public class OtpController {
    @Autowired
    OtpService otpService;

    @PostMapping(path = "/get")
    public ResponseEntity<OtpDto> getOtp(@RequestHeader Map<String, String> headerss, @RequestBody EmailDTO emailDTO) {

        headerss.forEach((key, value) -> {
            System.out.println(String.format("Header '%s' = %s", key, value));
        });
        OtpDto otpDto = null;
        try {
            otpDto = otpService.sendEmail(emailDTO.getUserEmail());
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<OtpDto>(otpDto, HttpStatus.OK);

    }
}
