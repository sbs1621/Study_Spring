package hello.hellospring.repository;

import hello.hellospring.domin.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

public class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach //메소드가 끝날때 마다 어떠한 동작을 한다. 콜백 메소드
    public void afterEach(){
        repository.clearStore();
    }

    @Test
    //테스트한다")
    public void save(){
        Member member = new Member();
        member.setName("sping");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();
        //assertEquals(member, result);
        assertThat(member).isEqualTo(result);
    }
    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);


        Member result = repository.findByname("spring1").get();
        assertThat(result).isEqualTo(member1);
        assertEquals(result,member1);
    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();
        assertThat(result.size()).isEqualTo(2);
    }


}
