package com.lion.bonobono.service;


import com.lion.bonobono.dto.MakeQusReq;
import com.lion.bonobono.dto.ResultDto;
import com.lion.bonobono.repository.BonoBonoRepository;
import com.lion.bonobono.repository.Member;
import com.lion.bonobono.repository.Question;
import com.lion.bonobono.repository.Result;
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
        String alpha = "abcdefghijklmnopqrlsuvwxwzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        String code = "";
        while(true) {
            for (int i = 0; i < 8; i++) {
                Integer tmp = rand.nextInt(62);
                code += alpha.charAt(tmp);
            }

            if(!bonoRepository.findByUrl(code).isPresent()) {
                break;
            }
        }

        List<Question> list =  new ArrayList<Question>();

        for(int i =0;i<10;i++){
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

    public String makeResult(String url, String name, Integer score)throws Exception{
        Member member = bonoRepository.findByUrl(url)
                .orElseThrow(()->new Exception("유저가 존재하지 않습니다."));

        Result result = new Result(name, score, member);

        member.getResults().add(result);

        bonoRepository.save(member);

        return "등록 완료";
    }

    public List<ResultDto> getRank(String url)throws Exception{
        Member member = bonoRepository.findByUrl(url)
                .orElseThrow(()->new Exception("유저가 존재하지 않습니다."));

        List<ResultDto> results =member.getResults().stream().sorted((o1,o2)->{
            if(o1.getScore()>o2.getScore()){
                return -1;
            }
            else if (o1.getScore() == o2.getScore()){
                return 0;
            }
            else
                return 1;
        }).map(o->new ResultDto(o.getName(),o.getScore())).collect(Collectors.toList());

        Integer m = results.get(0).getScore();
        Integer rank = 1;

        for(ResultDto r : results){
            if(r.getScore()<m){
                rank = rank + 1;
                m = r.getScore();
            }
            r.setRank(rank);
        }

        return results;

    }
}
