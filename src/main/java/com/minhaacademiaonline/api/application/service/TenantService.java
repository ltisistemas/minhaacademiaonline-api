package com.minhaacademiaonline.api.application.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.encoder.QRCode;
import com.minhaacademiaonline.api.adapters.in.web.exceptions.TenantNotFoundException;
import com.minhaacademiaonline.api.application.interfaces.ITenantService;
import com.minhaacademiaonline.api.adapters.in.web.dto.TenantCreateDto;
import com.minhaacademiaonline.api.adapters.in.web.dto.UserTenantAssignCreateDto;
import com.minhaacademiaonline.api.domain.entities.Tenant;
import com.minhaacademiaonline.api.domain.entities.UserTenant;
import com.minhaacademiaonline.api.infra.repositories.TenantRepository;
import com.minhaacademiaonline.api.infra.repositories.UserTenantRepository;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TenantService implements ITenantService {
    @Value("${base.url_domain}")
    private String _baseUrlDomain;
    private final TenantRepository _repository;
    private final UserTenantRepository _userTenantRepository;
//    private final ApplicationEventPublisher _applicationEventPublisher;

    @Override
    public Tenant create(TenantCreateDto req) {
        Tenant ten = new Tenant();
        ten.setLegalName(req.legalName());
        ten.setTradeName(req.tradeName());
        ten.setNif(req.nif());
        ten.setSubdomain(ten.generteSubdomain());
        ten.setPlan(req.plan());
        ten.setMonthlyFee(req.plan().getFee());
        ten.setPaidStatus(req.paidStatus());

        var tenant = _repository.save(ten);
//        _applicationEventPublisher.publishEvent(
//                new TenantCreatedEvent(
//                        tenant.getId(),
//                        req.plan().getId()
//                )
//        );

        return tenant;
    }

    public UserTenant userAssign(UserTenantAssignCreateDto req) {
        UserTenant ut = new UserTenant();
        ut.setUser(req.user());
        ut.setTenant(req.tenant());
        ut.setRole(req.role());

        return _userTenantRepository.save(ut);
    }

    @Override
    public List<Tenant> findAll() {
        return List.of();
    }

    @Override
    public Tenant findById(UUID id) {
        return null;
    }

    @Override
    public @Nullable Tenant findTenantBySubdomain(String subdomain) {
        return _repository.findTenantBySubdomain(subdomain).orElse(null);
    }

    @Override
    public @Nullable Tenant findTenantWithSubscription(UUID id) {
        return (Tenant) _repository.findTenantById(id).orElse(null);
    }

    @Override
    public @Nullable Tenant findTenantWithSubscription(String subdomain) {
        return _repository.findTenantBySubdomain(subdomain).orElse(null);
    }

    public String getUrl(String subdomain) {
        return "http://" + subdomain + "." + _baseUrlDomain;
    }
    public String getPublicRegisterStudent(String subdomain) {
        Tenant tenant = findTenantBySubdomain(subdomain);
        if (tenant != null) {
            var url = getUrl(subdomain) + "/api/v1/student/sing-up";
        }

        throw new TenantNotFoundException("Tenant not Found");
    }
    @Override
    public byte[] generateQrCode(String subdomain, int width, int height) throws WriterException, IOException {
        Tenant tenant = findTenantBySubdomain(subdomain);
        if (tenant != null) {
            QRCodeWriter qrCodeWriter = new QRCodeWriter();

            String url = getUrl(subdomain);

            BitMatrix bitMatrix = qrCodeWriter.encode(url, BarcodeFormat.QR_CODE, width, height);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            MatrixToImageWriter.writeToStream(bitMatrix, "PNG", outputStream);
            return outputStream.toByteArray();
        }

        throw new TenantNotFoundException("Tenant not Found");
    }
}
