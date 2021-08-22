package com.rpontello.clones.bitly.modules.url.service;

import com.rpontello.clones.bitly.database.entities.UrlAvailable;
import com.rpontello.clones.bitly.database.entities.UrlRegister;
import com.rpontello.clones.bitly.database.entities.User;
import com.rpontello.clones.bitly.database.repositories.UrlAvailableRepository;
import com.rpontello.clones.bitly.database.repositories.UrlRegisterRepository;
import com.rpontello.clones.bitly.database.repositories.UserRepository;
import com.rpontello.clones.bitly.models.dto.UrlRegisterDTO;
import com.rpontello.clones.bitly.models.enums.UserTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UrlServiceImpl implements UrlService{

    @Value("${application.domain.url}")
    private String urlDomain;

    @Autowired
    private UrlRegisterRepository urlRegisterRepository;

    @Autowired
    private UrlAvailableRepository urlAvailableRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public String createShortUrl(UrlRegisterDTO urlRegisterDTO, Principal principal) {

        User loggedUser = getLoggedUser(principal);

        UrlAvailable urlAvailable = getUrlAvailable(loggedUser, urlRegisterDTO.getCustomUrl());

        try {
            String url = urlRegisterDTO.getBaseUrl();
            UrlRegister urlRegister = urlRegisterRepository.save(new UrlRegister(url, urlAvailable, loggedUser));
            return urlDomain.concat("/").concat(urlRegister.getUrlAvailable().getUrl());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Couldn't create a short url");
        }
    }

    @Override
    public String getFullUrl(String url) {
        Optional<UrlRegister> urlRegistered = urlRegisterRepository.findByUrlAvailable_Url(url);

        if (urlRegistered.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "URL not found");
        }

        updateAccess(urlRegistered.get());

        return urlRegistered.get().getUrlBase();
    }

    private void updateAccess(UrlRegister urlRegister) {
        urlRegister.setAccessTotal(urlRegister.getAccessTotal() + 1);
        urlRegister.getUrlAvailable().setLastAccess(LocalDateTime.now());
        urlRegisterRepository.save(urlRegister);
    }

    private UrlAvailable getUrlAvailable(User user, String customUrl) {

        UrlAvailable urlAvailable;

        if (UserTypeEnum.PREMIUM == user.getType() && !customUrl.isBlank()) {
            if (urlAvailableRepository.existsByUrl(customUrl)) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Custom url not available");
            }
            urlAvailable = urlAvailableRepository.save(new UrlAvailable(customUrl));
        } else {
            Optional<UrlAvailable> url = userRepository.findFirstByIsAvailable(true);
            if (url.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No url available");
            }
            urlAvailable = url.get();
        }

        return urlAvailable;
    }

    private User getLoggedUser(Principal principal) {
        Optional<User> loggedUser =  userRepository.findByUsername(principal.getName());

        if (loggedUser.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "User haven't permission");
        }

        return loggedUser.get();
    }

}
