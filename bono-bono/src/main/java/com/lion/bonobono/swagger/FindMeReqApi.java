package com.lion.bonobono.swagger;


import io.swagger.v3.oas.annotations.Operation;


import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@Operation(summary = "Url을 전달해 주면 데이터를 제공", description = "제공된 url을 전달해주세요")
public @interface FindMeReqApi {
}
