package com.rpontello.clones.bitly.modules.url.controller;


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

    @PostMapping(value = "/shorting-url")
    private ResponseEntity<String> shortingUrl(@RequestBody String url, Principal principal) {
        String shortUrl = urlService.shortingUrl(url, principal);
        return ResponseEntity.status(HttpStatus.CREATED).body(shortUrl);
    }

    @GetMapping(value = "/{hash}")
    private ResponseEntity<String> getUrl(@PathParam("hash") String hash) {
        String shortUrl = urlService.getUrlByHash(hash);
        return ResponseEntity.ok(shortUrl);
    }

}
