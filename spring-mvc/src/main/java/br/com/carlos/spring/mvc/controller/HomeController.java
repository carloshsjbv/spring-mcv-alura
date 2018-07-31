package br.com.carlos.spring.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping("/")
    public String index() {

        System.out.println("Entrando na home");
        return "home";

    }

}
