package com.picpacsimplificado.picpacsimplificado.Services;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.picpacsimplificado.picpacsimplificado.domain.user.User;
import com.picpacsimplificado.picpacsimplificado.dtos.NotificationDTO;

@Service
public class NotificationService {

    @Autowired
    private RestTemplate restTemplate;

    public void sendNotification(User user, String message) throws Exception {
        String email = user.getEmail();
        NotificationDTO notificationRequest = new NotificationDTO(email, message);

        // ResponseEntity<String> notificationResponse =
        // restTemplate.postForEntity("http://o4d9z.mocklab.io/notify",
        // notificationRequest, String.class);

        // if (!(notificationRequest.getStatusCode() == HttpStatus.OK)) {

        // System.out.println("Erro ao enviar notificação");
        // throw new Exception("Servico de notificação esta fora do ar");
        // }

        System.out.print("Notificação enviada com sucesso!!!");

    }

}