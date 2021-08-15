package com.rpontello.clones.bitly.modules.url.service;

import com.rpontello.clones.bitly.database.entities.UrlAvailable;
import com.rpontello.clones.bitly.database.entities.UrlRegister;
import com.rpontello.clones.bitly.database.entities.User;
import com.rpontello.clones.bitly.database.repositories.UrlRegisterRepository;
import com.rpontello.clones.bitly.database.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.Optional;

@Service
public class UrlServiceImpl implements UrlService{

    @Value("${application.domain.url}")
    private String urlDomain;

    @Autowired
    private UrlRegisterRepository urlRegisterRepository;

    @Autowired
    private UrlAvailable urlAvailable;

    @Autowired
    private UserRepository userRepository;

    @Override
    public String createShortUrl(String url, Principal principal) {

        User loggedUser = getLoggedUser(principal);

        UrlAvailable urlAvailable = getUrlUrlAvailable();

        try {
            UrlRegister urlRegister = urlRegisterRepository.save(new UrlRegister(url, urlAvailable, loggedUser));
            return urlDomain.concat("/").concat(urlRegister.getUrlAvailable().getUrlHash());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Couldn't create a short url");
        }
    }

    @Override
    public String getUrlByHash(String hash) {
        Optional<UrlRegister> url = urlRegisterRepository.findByUrlAvailable_UrlHash(hash);

        if (url.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "URL not found");
        }

        return url.get().getUrlBase();
    }

    private UrlAvailable getUrlUrlAvailable() {
        Optional<UrlAvailable> urlAvailable = userRepository.findFirstByIsAvailable(true);

        if (urlAvailable.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No url available");
        }

        return urlAvailable.get();
    }

    private User getLoggedUser(Principal principal) {
        Optional<User> loggedUser =  userRepository.findByName(principal.getName());

        if (loggedUser.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "User haven't permission");
        }

        return loggedUser.get();
    }

}
