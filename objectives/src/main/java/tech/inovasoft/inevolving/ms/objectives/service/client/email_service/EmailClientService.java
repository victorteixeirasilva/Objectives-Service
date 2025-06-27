package tech.inovasoft.inevolving.ms.objectives.service.client.email_service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import tech.inovasoft.inevolving.ms.objectives.service.client.email_service.dto.EmailRequest;


@FeignClient(name = "email-service", url = "${inevolving.uri.ms.email}")
public interface EmailClientService {

    @PostMapping
    ResponseEntity<String> sendEmail(@RequestBody EmailRequest request);

}
