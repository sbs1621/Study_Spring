package hello.hellospring.service;

import hello.hellospring.domain.Member;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberService= new MemberService(memberRepository);
    }

    @AfterEach //메소드가 끝날때 마다 어떠한 동작을 한다. 콜백 메소드
    public void afterEach(){
        memberRepository.clearStore();//메모리 클리어
    }

    @Test
    void join() {
        //given
        Member member = new Member();
        member.setName("hello");
        //when
        Long saveId = memberService.join(member);
        //then
        Member findMember = memberService.findOne(saveId).get();
        Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복회원예외(){
        //given
        Member member1 = new Member();
        member1.setName("spring");
        Member member2 = new Member();
        member2.setName("spring");
        //when
        memberService.join(member1);
        //예외가 발생하면 성공
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");


        //assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        /*try{
            memberService.join(member2);
            fail();
        }catch(IllegalStateException e){
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }*/
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}