package com.jojoIdu.book.springboot;

import com.jojoIdu.book.springboot.web.HelloController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@WebMvcTest(controllers = HelloController.class)
public class HelloControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void hello_is_return() throws Exception {
        String hello = "hello";

        mvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string(hello));
    }

    @Test
    public void helloDto_is_return() throws Exception{
        String name ="hello";
        int amount = 1000;

        mvc.perform(
                        get("/hello/dto")
                                //parme은 api테스트 할 때 사용할 요청 파라미터를 설정하는 것 이다.
                                //string값만 설정할 수 있으며 int등의 값을 받고싶을땐 문자열로 변경해야 한다.
                                .param("name",name)
                                .param("amount",String.valueOf(amount)))
                .andExpect(status().isOk())
                //sjonpath는 json응답값을 필드별로 검증할 수 있는 메소드 이다.
                //$를 기준으로 필드명을 명시한다. 여기서는 name, amount를 검증한다.
                .andExpect(jsonPath("$.name", is(name)))
                .andExpect(jsonPath("$.amount", is(amount)));
    }
}