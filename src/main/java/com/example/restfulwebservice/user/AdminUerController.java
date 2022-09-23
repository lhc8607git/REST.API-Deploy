package com.example.restfulwebservice.user;


import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminUerController {
    private UserDaoService service;


    public AdminUerController(UserDaoService service) {
        this.service = service;
    }

    // 관리자 전체 목록 조회
    @GetMapping("/users")
    public MappingJacksonValue retrieveAllUsers() {
        // 필터링된 데이터 값을 반환하기 위해서는 -> MappingJacksonValue 사용

        List<User> users = service.findAll();

        // 해당 값들만 필터해서 얻음
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter
                .filterOutAllExcept("id", "name", "joinDate", "password");   //"ssn"

        FilterProvider filters = new SimpleFilterProvider().addFilter("UserInfo", filter);
                                                                      //User클래스의 @JsonFilter 값

        MappingJacksonValue mapping = new MappingJacksonValue(users);
        mapping.setFilters(filters);

        return mapping;
    }


    // 관리자 개별 사용자 목록
    // GET /admin/users/1 -> 컨트롤러는 String으로 전달 받습니다.
    // GET /admin/users/1 ->   /admin/v1/users/1   (버전 적용함)
    //@GetMapping("/v1/users/{id}")                            // 버전_첫번째 방법
    //@GetMapping(value = "/users/{id}/", params = "version=1") // 버전_두번째 방법
    //@GetMapping(value = "/users/{id}", headers="X-API-VERSION=1") // 버전_셋번째 방법
    @GetMapping(value = "/users/{id}", produces = "application/vnd.company.appv1+json") // 버전_넷번째 방법
    public MappingJacksonValue retrieveUserV1(@PathVariable int id) {   // int로 매핑이 자동으로 됩니다.
        User user = service.findOne(id);

        if (user == null) {
            throw new UserNotFoundException(String.format("ID[%s] not found", id));
        }

        // 해당 값들만 필터해서 얻음
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter
                .filterOutAllExcept("id", "name", "password", "ssn");   //"ssn"

        FilterProvider filters = new SimpleFilterProvider().addFilter("UserInfo", filter);
        //User클래스의 @JsonFilter 값

        MappingJacksonValue mapping = new MappingJacksonValue(user);
        mapping.setFilters(filters);


        return mapping;
    }

    // 버전 연습 하기 위한 v2 코드 (지워도 상관없음)
    //@GetMapping("/v2/users/{id}")                           // 버전_첫번째 방법
    //@GetMapping(value = "/users/{id}/", params = "version=2") // 버전_두번째 방법
    //@GetMapping(value = "/users/{id}", headers="X-API-VERSION=2") // 버전_셋번째 방법
    @GetMapping(value = "/users/{id}", produces = "application/vnd.company.appv2+json") // 버전_넷번째 방법
    public MappingJacksonValue retrieveUserV2(@PathVariable int id) {   // int로 매핑이 자동으로 됩니다.
        User user = service.findOne(id);

        if (user == null) {
            throw new UserNotFoundException(String.format("ID[%s] not found", id));
        }


        // User -> UserV2
        UserV2 userV2 = new UserV2();
        BeanUtils.copyProperties(user, userV2); // id, name, joinDate, password, ssn    // 빈 관련 클래스
        userV2.setGrade("VIP");



        // 해당 값들만 필터해서 얻음
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter
                .filterOutAllExcept("id", "name", "joinDate", "grade");   //"ssn"

        FilterProvider filters = new SimpleFilterProvider().addFilter("UserInfoV2", filter);
        //UserV2클래스의 @JsonFilter 값

        MappingJacksonValue mapping = new MappingJacksonValue(userV2);
        mapping.setFilters(filters);


        return mapping;
    }



}
