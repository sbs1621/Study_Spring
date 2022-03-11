package hello.helloController.Service;


import hello.helloController.Controller.dto.PostsSaveRequestDto;
import hello.helloController.Controller.dto.PostsUpdateRequestDto;
import hello.helloController.domain.posts.Posts;
import hello.helloController.domain.posts.PostsRepository;
import hello.helloController.exception.DuplicatePostException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Posts getById(Long id) {
        return postsRepository.findById(id).get();
    }

    @Transactional
    public Long save(PostsSaveRequestDto requestDto){
        return postsRepository.save(requestDto.Entity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = validateDuplicatePost(id);
        posts.update(requestDto.getTitle(), requestDto.getContent());
        return id;
    }

    private Posts validateDuplicatePost(Long id) {
        return postsRepository.findById(id)
                .orElseThrow(() -> new DuplicatePostException("해당 사용자가 없습니다 id = " + id));

    }

}
