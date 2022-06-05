package com.jojoIdu.book.springboot.domain.posts;

import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @After //(1)
    public void cleanup(){
        postsRepository.deleteAll();
    }

    @Test
    public void 게시글저장_불러오기() {
        //given
        String title ="테스트 게시글";
        String content ="테스트 본문";

        postsRepository.save(Posts.builder() //(2)
                .title(title)
                .content(content)
                .author("ymh7701@kakao.com")
                .build());

        //when
        List<Posts> postsList = postsRepository.findAll(); //(3)

        //then
        Posts posts=postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);

    }

    @Test
    public void BaseTimeEntity_insert(){
        // 1. 테스트 값 설정
        LocalDateTime now = LocalDateTime.of(2019,6,4,0,0,0);
        postsRepository.save(Posts.builder()
                            .title("baseTimeTitle")
                            .content("baseTimeContent")
                            .author("baseTimeAuthor")
                            .build());

        // 2. 테스트 값 조회
        List<Posts> postsList = postsRepository.findAll();

        // 3. 테스트 값 확인
        Posts posts = postsList.get(0);

        System.out.println(">>>>>>> 생성일자 : " + posts.getCreatedDate()
                            + " 수정일자 : " + posts.getModifiedDate());

        assertThat(posts.getCreatedDate()).isAfter(now);
        assertThat(posts.getModifiedDate()).isAfter(now);
    }
}