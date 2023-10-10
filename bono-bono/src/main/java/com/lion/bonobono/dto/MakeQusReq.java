package com.lion.bonobono.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Schema(name = "질문, 정답, 이름 데이터")
public class MakeQusReq {

    @Schema(example = "hee")
    private String name;

    @Schema(example = "[1,1,2,3,4,5,6,7]")
    private List<Integer> questions;

    @Schema(example = "[1,2,3,4,5,6,7,8]")
    private List<Integer> answers;
}
