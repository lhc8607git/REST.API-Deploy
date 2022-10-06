package com.example.restfulwebservice.testrest;

import com.example.restfulwebservice.helloworld.HelloWorldBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@Controller
public class GuestController {

    @RequestMapping("/guests")
    public String getGuests() {
        return "aaa.html";
    }

}
