package com.pokemon.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class StatsDto {

    private String base_stat;
    private String effort;
    private String statName;
    private String statUrl;



    @SuppressWarnings("unchecked")
    @JsonProperty("stat")
    private void unpackNested(Map<String, Object> stats) {
        this.statName = (String) stats.get("name");
        this.statUrl = (String) stats.get("url");
        }

    public String getStatName() {
        return statName;
    }

    public void setStatName(String statName) {
        this.statName = statName;
    }

    public String getStatUrl() {
        return statUrl;
    }

    public void setStatUrl(String statUrl) {
        this.statUrl = statUrl;
    }

    public StatsDto() {
    }

    public String getBase_stat() {
        return base_stat;
    }

    public void setBase_stat(String base_stat) {
        this.base_stat = base_stat;
    }

    public String getEffort() {
        return effort;
    }

    public void setEffort(String effort) {
        this.effort = effort;
    }
}
