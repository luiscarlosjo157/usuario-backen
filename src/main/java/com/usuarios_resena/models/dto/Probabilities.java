package com.usuarios_resena.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Probabilities {
    @JsonProperty("positive")
    private double positive;

    @JsonProperty("negative")
    private double negative;

    @JsonProperty("neutral")
    private double neutral;

    public double getPositive() {
        return positive;
    }

    public void setPositive(double positive) {
        this.positive = positive;
    }

    public double getNegative() {
        return negative;
    }

    public void setNegative(double negative) {
        this.negative = negative;
    }

    public double getNeutral() {
        return neutral;
    }

    public void setNeutral(double neutral) {
        this.neutral = neutral;
    }
    
}
