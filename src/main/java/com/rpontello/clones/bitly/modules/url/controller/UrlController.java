package com.rpontello.clones.bitly.modules.url.controller;


import com.rpontello.clones.bitly.models.dto.UrlRegisterDTO;
import com.rpontello.clones.bitly.modules.url.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.security.Principal;

@RestController
public class UrlController {

    @Autowired
    private UrlService urlService;

    @PostMapping(value = "/short-url")
    private ResponseEntity<String> createShortUrl(
            @RequestBody UrlRegisterDTO urlRegisterDTO,
            Principal principal
    ) {
        String shortUrl = urlService.createShortUrl(urlRegisterDTO, principal);
        return ResponseEntity.status(HttpStatus.CREATED).body(shortUrl);
    }

    @GetMapping(value = "/{url}")
    private ResponseEntity<String> getUrl(@PathParam("url") String url) {
        return ResponseEntity.ok(urlService.getFullUrl(url));
    }

}
