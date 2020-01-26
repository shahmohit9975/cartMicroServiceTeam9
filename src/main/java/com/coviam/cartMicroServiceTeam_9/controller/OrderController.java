package com.coviam.cartMicroServiceTeam_9.controller;


import com.coviam.cartMicroServiceTeam_9.dto.*;
import com.coviam.cartMicroServiceTeam_9.entity.OrderDetails;
import com.coviam.cartMicroServiceTeam_9.entity.UserOrder;
import com.coviam.cartMicroServiceTeam_9.service.OrderService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;


    @PostMapping(path = "/place")
    private ResponseEntity<?> placeOrder(@Valid @RequestBody UserOrder userOrder) {
        synchronized (this) {
            StatusCheckForOrderDTO checkQuantityForOrders = orderService.checkQuantity(userOrder.getOrderDetails());
            if (checkQuantityForOrders.isStatus()) {

                final UserOrder save = orderService.save(userOrder);
                ArrayList<DecreaseMerchantProductQuantityDTO> list = new ArrayList<DecreaseMerchantProductQuantityDTO>();
                ArrayList<EmailOrderDetailsDTO> emailOrderDetailsDTOSList = new ArrayList<EmailOrderDetailsDTO>();
                for (OrderDetails o : userOrder.getOrderDetails()) {
                    EmailOrderDetailsDTO emailOrderDetailsDTO = new EmailOrderDetailsDTO();
                    BeanUtils.copyProperties(o, emailOrderDetailsDTO);
                    emailOrderDetailsDTOSList.add(emailOrderDetailsDTO);
                    DecreaseMerchantProductQuantityDTO obj = new DecreaseMerchantProductQuantityDTO();
                    obj.setMerchantAndProductId(o.getMerchantAndProductId());
                    obj.setQuantity(o.getQuantity());
                    list.add(obj);
                }

                orderService.decreaseQuantityAfterOrderPlaced(list);
                System.out.println("decreaseQuantityAfterOrderPlaced successfully done ...");
                try {
                    orderService.sendEmail(save.getOrderId(), save.getUserEmail(), save.getAmount(), save.getOrderDate(), emailOrderDetailsDTOSList);
                } catch (MessagingException e) {

                    e.printStackTrace();
                }
                return new ResponseEntity<>(checkQuantityForOrders, HttpStatus.ACCEPTED);
            }
            return new ResponseEntity<>(checkQuantityForOrders, HttpStatus.NOT_ACCEPTABLE);

        }
    }

    @GetMapping(path = "/get/{userEmail}")
    public ResponseEntity<?> getOrderDetails(@PathVariable String userEmail) {
        List<UserOrder> userOrderDTO = orderService.getUserOrderDetails(userEmail);
        return new ResponseEntity<>(userOrderDTO, HttpStatus.OK);
    }


}
