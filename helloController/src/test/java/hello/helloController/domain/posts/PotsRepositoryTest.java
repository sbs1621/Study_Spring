package hello.helloController.domain.posts;

import org.aspectj.lang.annotation.After;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;

import java.util.List;

@SpringBootTest
class PotsRepositoryTest {

    @Autowired
    PostsRepository potsRepository;

    @After("")
    public void cleanup(){
        potsRepository.deleteAll();
    }

    @Test
    public void 글_불러오기(){
        //given
        String title =  "테스트 게시글";
        String content = "테스트 본문";

        potsRepository.save(Posts.builder()
                .title(title)
                .content(content)
                .author("jojoldu@gmail.com")
                .build());
        //when
        List<Posts> postsList = potsRepository.findAll();

        //then
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }
}