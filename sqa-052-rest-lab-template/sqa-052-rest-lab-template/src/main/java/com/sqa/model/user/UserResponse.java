package com.sqa.model.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.sqa.model.Meta;
import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "meta",
        "data"
})
public class UserResponse {

    @JsonProperty("meta")
    private Meta meta;
    @JsonProperty("data")
    private User data = null;

}
