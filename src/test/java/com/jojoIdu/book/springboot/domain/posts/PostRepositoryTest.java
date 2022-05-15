package com.jojoIdu.book.springboot.domain.posts;

import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
public class PostRepositoryTest {
    @Autowired
    PostRepository postRepository;

    @After
    public void cleanup(){
        postRepository.deleteAll();;
    }

    public void 게시글저장_불러오기(){
        //값 세팅
        String title = "테스트 게시글";
        String content = "테스트 본문";

        postRepository.save(Posts.builder().title(title).content(content).author("jojoIdu@gmail.com").build());

        List<Posts> postsList = postRepository.findAll();

        Posts posts = postsList.get(0);

        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }
}
