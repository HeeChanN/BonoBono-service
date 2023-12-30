package com.lion.bonobono.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ResultDto {

    private Integer rank;



    private String name;
    private Integer score;

    public ResultDto(String name, Integer score) {
        this.name = name;
        this.score = score;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }
}
