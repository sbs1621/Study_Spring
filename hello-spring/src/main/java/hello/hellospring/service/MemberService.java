package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;

@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    //맴버 서비스와 맴버 리포지토리를 연결시켜줌
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    //회원가입
    public long join(Member member) {

        long start = System.currentTimeMillis();

        try{

        //같은 이름이 있는 중복회원X
        //cmd + option + v ->optional<Member>생성
        //보기 안좋기 떄문에 아래의 코드로 수정
        validateDuplicateMember(member);//중복 회원 검증
        memberRepository.save(member);
        return member.getId();}finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("join " + timeMs + "ms");
        }
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByname(member.getName())
                .ifPresent(m->{
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
    }

    //전체회원조회
    public List<Member> findMembers(){
        long start = System.currentTimeMillis();

        try {
            return memberRepository.findAll();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("findMembers " + timeMs + "ms");
        }
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById((memberId));
    }

}
