package com.lion.bonobono.swagger;


import com.lion.bonobono.dto.MakeQusReq;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.http.MediaType;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@Operation(summary = "Url 생성", description = "이름과 각 질문의 인덱스 번호를 1번부터 시작한 값과 해당하는 정답 인덱스 전달해 주세요")
@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Url 생성 데이터", required = true, content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
        schema = @Schema(implementation = MakeQusReq.class)))
public @interface MakeMeReqApi {
}
