package com.minhaacademiaonline.api.adapters.in.web.controllers;

import com.google.zxing.WriterException;
import com.minhaacademiaonline.api.application.interfaces.ITenantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("api/v1/auth/qr-code")
@RequiredArgsConstructor
public class QrCodeController {
    private final ITenantService _TenantService;

    @GetMapping("get-tenant/{subdomain}")
    public ResponseEntity<byte[]> generateQrCodeTenant(@PathVariable String subdomain) throws IOException, WriterException {
        return ResponseEntity.ok(_TenantService.generateQrCode(subdomain, 800, 800));
    }

    // generateQrCodeForStudentSignUp
    @GetMapping("get-student-sign-up/{subdomain}")
    public ResponseEntity<byte[]> generateQrCodeForStudentSignUp(@PathVariable String subdomain) throws IOException, WriterException {
        return ResponseEntity.ok(_TenantService.generateQrCodeForStudentSignUp(subdomain, 800, 800));
    }
}
