package com.sqa.model.user;

import com.fasterxml.jackson.annotation.*;
import com.sqa.model.Meta;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Data
@Getter
@Setter
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "meta",
        "data"
})
public class Users {

    @JsonProperty("meta")
    public Meta meta;
    @JsonProperty("data")
    public List<User> data = null;
}