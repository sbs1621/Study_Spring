package hello.helloController.Service;


import com.samskivert.mustache.Mustache;
import hello.helloController.Controller.dto.PostsListResponseDto;
import hello.helloController.Controller.dto.PostsResponseDto;
import hello.helloController.Controller.dto.PostsSaveRequestDto;
import hello.helloController.Controller.dto.PostsUpdateRequestDto;
import hello.helloController.domain.posts.Posts;
import hello.helloController.domain.posts.PostsRepository;
import hello.helloController.exception.DuplicatePostException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collector.*;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;


    @Transactional
    public Posts getById(Long id) {
        return postsRepository.findById(id).get();
    }

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.Entity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = validateDuplicatePost(id);
        posts.update(requestDto.getTitle(), requestDto.getContent());
        return id;
    }

    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("해당 사용자가 없습니다."));
        return new PostsResponseDto(entity);
    }

    private Posts validateDuplicatePost(Long id) {
        return postsRepository.findById(id)
                .orElseThrow(() -> new DuplicatePostException("해당 사용자가 없습니다 id = " + id));

    }

    @Transactional//(readOnly = true)
    public List<PostsListResponseDto> findAllDesc() {
        return postsRepository.findAllDesc()
                .stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long id) {
        Posts posts = postsRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("해당 게시글이 없습니다."));
        postsRepository.delete(posts);
    }

}
