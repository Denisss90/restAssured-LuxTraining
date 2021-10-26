package com.sqa.model;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.Generated;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "total",
        "pages",
        "page",
        "limit",
        "links"
})
public class Pagination {

    @JsonProperty("total")
    private Integer total;
    @JsonProperty("pages")
    private Integer pages;
    @JsonProperty("page")
    private Integer page;
    @JsonProperty("limit")
    private Integer limit;
    @JsonProperty("links")
    private Links links;
}