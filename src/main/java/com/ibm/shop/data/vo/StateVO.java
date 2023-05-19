package com.ibm.shop.data.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.ibm.shop.entities.Country;
import jakarta.persistence.*;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@JsonPropertyOrder({"id", "name"})
public class StateVO extends RepresentationModel<StateVO> implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @JsonProperty("id")
    private Long key;

    private String name;

    public StateVO() {}

    public Long getKey() {
        return key;
    }

    public void setKey(Long key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StateVO stateVO)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(getKey(), stateVO.getKey());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getKey());
    }
}
