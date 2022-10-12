package com.example.restfulwebservice.testrest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/guests")
public class GuestController {

//    @GetMapping("/guests")
//    public String getGuests() {
//        return "guests";
//    }

//    @RequestMapping("/index")
//    public String getIndex() {
//        return "index222.html";
//    }




    @GetMapping
    public void getGuests() {
    }



}


