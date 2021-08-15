package com.rpontello.clones.bitly.modules.url.service;

import java.security.Principal;

public interface UrlService {
    String createShortUrl(String url, Principal principal);
    String getUrlByHash(String hash);
}
