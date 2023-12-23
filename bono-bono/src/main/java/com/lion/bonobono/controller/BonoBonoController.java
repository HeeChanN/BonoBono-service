package com.lion.bonobono.controller;


import com.lion.bonobono.dto.MakeQusReq;
import com.lion.bonobono.service.BonoBonoService;
import com.lion.bonobono.swagger.FindMeReqApi;
import com.lion.bonobono.swagger.FineMeResApi;
import com.lion.bonobono.swagger.MakeMeReqApi;
import com.lion.bonobono.swagger.MakeMeResApi;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins="*", allowedHeaders = "*")
@RestController
@RequiredArgsConstructor
public class BonoBonoController {

    private final BonoBonoService bonoBonoService;

    /** 질문 8개에 해당하는 답 8개가 들어올 예정
     *
     *  이에 대한 Response로 난 랜덤으로 알파벳 조합으로 code를 보낼 예정
     * */

    @MakeMeReqApi
    @MakeMeResApi
    @PostMapping("/make-me")
    public ResponseEntity<?> makeQuestion(@RequestBody MakeQusReq makeQusReq){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(bonoBonoService.makeUrl(makeQusReq));
    }


    @FindMeReqApi
    @FineMeResApi
    @GetMapping("/find-me/{code}")
    public ResponseEntity<?> findMe(@PathVariable(name ="code") String code)
    {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(bonoBonoService.findMember(code));
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }

    @PostMapping("/result/{code}")
    public ResponseEntity<?> result(@PathVariable(name ="code") String code,
                                    @RequestParam("name") String name,
                                    @RequestParam("score") Integer score)
    {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(bonoBonoService.makeResult(code,name,score));
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }

    @GetMapping("/{code}/rank")
    public ResponseEntity<?> getRank(@PathVariable(name ="code") String code)
    {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(bonoBonoService.getRank(code));
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }

}
