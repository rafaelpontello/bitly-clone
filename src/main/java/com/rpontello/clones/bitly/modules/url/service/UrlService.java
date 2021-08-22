package com.rpontello.clones.bitly.modules.url.service;

import com.rpontello.clones.bitly.models.dto.UrlRegisterDTO;

import java.security.Principal;

public interface UrlService {
    String createShortUrl(UrlRegisterDTO urlRegisterDTO, Principal principal);
    String getFullUrl(String url);
}
