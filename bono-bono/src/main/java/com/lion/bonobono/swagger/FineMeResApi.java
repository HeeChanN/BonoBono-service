package com.lion.bonobono.swagger;


import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


@ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "요청이 성공한 경우",
                content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                        examples = {
                                @ExampleObject(name = "성공 예제",
                                        value = "{\"name\": \"hee\", \"questions\": [1, 1, 2, 3, 4, 5, 6, 7], \"answers\": [1, 2, 3, 4, 5, 6, 7, 8]}",
                                        summary = "성공 예제", description = "요청이 성공한 경우의 예제입니다.")
                        })),
        @ApiResponse(responseCode = "404", description = "유저가 존재하지 않습니다.")
})
@Retention(RetentionPolicy.RUNTIME)
public @interface FineMeResApi {
}
