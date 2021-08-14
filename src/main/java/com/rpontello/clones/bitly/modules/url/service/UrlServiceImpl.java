package com.rpontello.clones.bitly.modules.url.service;

import com.rpontello.clones.bitly.database.repositories.UrlRegisterRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.security.Principal;

public class UrlServiceImpl implements UrlService{

    @Autowired
    private UrlRegisterRepository urlRegisterRepository;

    @Override
    public String shortingUrl(String url, Principal principal) {
        return null;
    }

    @Override
    public String getUrlByHash(String hash) {
        return null;
    }

}
