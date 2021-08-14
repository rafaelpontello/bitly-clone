package com.rpontello.clones.bitly.modules.url.service;

import com.rpontello.clones.bitly.models.dto.UrlDTO;

import java.security.Principal;

public interface UrlService {
    String shortingUrl(String url, Principal principal);
    String getUrlByHash(String hash);
}
