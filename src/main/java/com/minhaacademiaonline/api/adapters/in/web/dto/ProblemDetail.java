package com.minhaacademiaonline.api.adapters.in.web.dto;

import lombok.Builder;

import java.net.URI;
import java.util.List;

@Builder
public record ProblemDetail(
        URI type,
        String title,
        Integer code,
        String status,
        String detail,
        URI instance,
        List<String> errors
) {
    public ProblemDetail {
        if (type == null) {
            type = URI.create("about:blank");
        }
    }
}
