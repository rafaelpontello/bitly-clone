package com.rpontello.clones.bitly.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class UrlDTO {

    @JsonProperty("id")
    private UUID id;

    @JsonProperty("shortUrl")
    private String shortUrl;

}
