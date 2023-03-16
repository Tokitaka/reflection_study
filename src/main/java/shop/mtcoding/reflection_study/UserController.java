package shop.mtcoding.reflection_study;


import org.springframework.web.bind.annotation.RequestMapping;


public class UserController {
    @RequestMapping(uri="/login")
    public void login(){
        System.out.println("login()");
    }
    @RequestMapping(uri="/join")
    public void join(){
        System.out.println("join()");
    }
    @RequestMapping(uri="/joinForm")
    public void joinForm(){
        System.out.println("joinForm()");
    }
}
