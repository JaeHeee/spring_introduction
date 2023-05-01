package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    // web application에서 '/hello'라고 들어오면 아래 method 실행함
    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "hello!!");
        return "hello";
    }

    // MVC, template engine 방식
    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam(value = "name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }

    // API 방식
    // ResponseBody 는 http 프로토콜에서의 body에 데이터를 직접 넣어주겠다.
    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name;
    }

    // 이렇게 하면 json 방식으로 데이터 나
    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);

        return hello;
    }

    // static class로 만들면 class내부에서 class를 만들어서 사용할 수 있다.
    static class Hello {
        private  String name;

        // getter, setter 는 자바빈 규약이다.
        // name이 private이니까 getter, setter 사용함
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
