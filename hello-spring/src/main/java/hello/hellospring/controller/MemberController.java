package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MemberController {

    private final MemberService memberService;

    @Autowired//맴버 서비스를 가져다가 연결 시켜줌줌
     public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}