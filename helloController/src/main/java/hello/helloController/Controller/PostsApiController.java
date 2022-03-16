package hello.helloController.Controller;

import hello.helloController.Controller.dto.PostsSaveRequestDto;
import hello.helloController.Controller.dto.PostsUpdateRequestDto;
import hello.helloController.Service.PostsService;
import hello.helloController.domain.posts.Posts;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PostsApiController {

    private final PostsService postsService;

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto) {
        return postsService.save(requestDto);
    }

    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable("id") Long id, @RequestBody PostsUpdateRequestDto requestDto) {
        return postsService.update(id, requestDto);
    }

    @GetMapping("/api/v1/posts/{id}")
    public @ResponseBody
    ResponseEntity getPost(@PathVariable("id") Long id) {
        Posts find = postsService.getById(id);
        return new ResponseEntity(find, HttpStatus.OK);
    }

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("posts", postsService.findAllDesc());
        return "index";
    }

}
