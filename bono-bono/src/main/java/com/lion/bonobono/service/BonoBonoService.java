package com.lion.bonobono.service;


import com.lion.bonobono.dto.MakeQusReq;
import com.lion.bonobono.repository.BonoBonoRepository;
import com.lion.bonobono.repository.Member;
import com.lion.bonobono.repository.Question;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BonoBonoService {

    private final BonoBonoRepository bonoRepository;

    @Transactional
    public String makeUrl(MakeQusReq makeQusReq){
        Random rand = new Random();
        String alpha = "abcdefghijklmnopqrlsuvwxwzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String code = "";
        while(true) {
            for (int i = 0; i < 8; i++) {
                Integer tmp = rand.nextInt(52);
                code += alpha.charAt(tmp);
            }

            if(!bonoRepository.findByUrl(code).isPresent()) {
                break;
            }
        }

        List<Question> list =  new ArrayList<Question>();

        for(int i =0;i<8;i++){
            list.add(Question.builder()
                            .content(makeQusReq.getQuestions().get(i)*10 + makeQusReq.getAnswers().get(i)).build());
        }
        Member member = Member.builder()
                .name(makeQusReq.getName())
                .url(code)
                .build();

        member.setQuestions(list);

        bonoRepository.save(member);
        return code;
    }

    public MakeQusReq findMember(String url) throws Exception
    {
        Member member = bonoRepository.findByUrl(url)
                .orElseThrow(()->new Exception("유저가 존재하지 않습니다."));

        List<Integer> questions = member.getQuestions()
                .stream().map(o->o.getContent()/10).collect(Collectors.toList());
        List<Integer> answers = member.getQuestions()
                .stream().map(o->o.getContent()%10).collect(Collectors.toList());

        return MakeQusReq.builder()
                .name(member.getName())
                .answers(answers)
                .questions(questions)
                .build();
    }
}