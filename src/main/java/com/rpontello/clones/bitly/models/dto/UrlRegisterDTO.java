package com.rpontello.clones.bitly.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UrlRegisterDTO {

    @JsonProperty("baseUrl")
    private String baseUrl;

    @JsonProperty("customUrl")
    private String customUrl = null;

}
