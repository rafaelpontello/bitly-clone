package com.rpontello.clones.bitly.modules.url.service;

public interface UrlService {
    String createShortUrl(String url);
    String getUrlByHash(String hash);
}
