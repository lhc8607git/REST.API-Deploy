package com.example.restfulwebservice.controller;



import com.example.restfulwebservice.Entity.User;
import com.example.restfulwebservice.service.UserDaoService;
import com.example.restfulwebservice.exception.UserNotFoundException;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@RestController
public class UserController {
    private UserDaoService service;


    public UserController(UserDaoService service) {
        this.service = service;
    }

    // 사용자 전체 목록 조회
    @GetMapping("/users")
    public List<User> retrieveAllUsers() {
        return service.findAll();
    }

    // 개별 사용자 목록
    // GET /users/1 or /users/10 -> 컨트롤러는 String으로 전달 받습니다.
    @GetMapping("/users/{id}")
    public EntityModel retrieveUser(@PathVariable int id) {   // int로 매핑이 자동으로 됩니다.
        User user = service.findOne(id);

        if (user == null) {
             throw new UserNotFoundException(String.format("ID[%s] not found", id));
        }

        //HATEOAS
        // HATEOAS 작업을 한다면 : 하나의 리소스에서 발생할 수 있는 여러가지 추가적인 작업도 확인 가능
        EntityModel<User> resource = new EntityModel<>(user);

        // 이 user값을 반환시킬때 user가 사용할수 있는 추가적인 정보 링크를 하이퍼링크로 넣어놓을 예정
        WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());

        resource.add(linkTo.withRel("all-users"));

        return resource;

    }

    // 사용자 추가(가입)
    @PostMapping("/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        User savedUser = service.save(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();

        return ResponseEntity.created(location).build();

    }

    // 사용자 삭제(탈퇴)
    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id) {
        User user = service.deleteById(id);

        if (user == null) {
            throw new UserNotFoundException(String.format("ID[%s] not found", id));
        }
    }




}
