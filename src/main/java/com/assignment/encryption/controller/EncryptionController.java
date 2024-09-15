package com.assignment.encryption.controller;

import com.assignment.encryption.dao.EncryptionRequest;
import com.assignment.encryption.dao.EncryptionResponse;
import com.assignment.encryption.service.EncryptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/encrypt")
public class EncryptionController {

    @Autowired
    private EncryptionService encryptionService;

    @PostMapping
    public ResponseEntity<EncryptionResponse> generateMessage(@RequestBody EncryptionRequest encryptionRequest) throws Exception {

        String encryptedMessage = encryptionService.encryptMessage(encryptionRequest.getMessage(),encryptionRequest.getEncryptionType());
        return ResponseEntity.ok(new EncryptionResponse(encryptedMessage));
    }
}
