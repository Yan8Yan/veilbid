package com.project.veilbid;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {
    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/lots")
    public String lots(){
        return "lots";
    }
}
