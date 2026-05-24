package com.usuarios_resena.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SentimentResponse {
    
    @JsonProperty("text_analyzed")
    private String textAnalyzed;

    @JsonProperty("sentiment")
    private String sentiment;

    @JsonProperty("probabilities")
    private Probabilities probabilities;

    public String getTextAnalyzed() {
        return textAnalyzed;
    }

    public void setTextAnalyzed(String textAnalyzed) {
        this.textAnalyzed = textAnalyzed;
    }

    public String getSentiment() {
        return sentiment;
    }

    public void setSentiment(String sentiment) {
        this.sentiment = sentiment;
    }

    public Probabilities getProbabilities() {
        return probabilities;
    }

    public void setProbabilities(Probabilities probabilities) {
        this.probabilities = probabilities;
    }


}
