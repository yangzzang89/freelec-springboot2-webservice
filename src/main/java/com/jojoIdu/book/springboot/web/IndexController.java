package com.jojoIdu.book.springboot.web;

import com.jojoIdu.book.springboot.service.posts.PostsService;
import com.jojoIdu.book.springboot.web.dto.PostsResponseDto;
import com.jojoIdu.book.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;

    @GetMapping(value = "/")
    public String index(Model model){
        model.addAttribute("posts",postsService.findAllDesc());

        return "index";
    }

    @GetMapping({"/posts/save"})
    public String postsSave(){
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model){
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post",dto);
        
        return "posts-update";
    }
}
