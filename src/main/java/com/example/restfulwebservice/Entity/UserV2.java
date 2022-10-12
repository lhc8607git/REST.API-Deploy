package com.example.restfulwebservice.Entity;

import com.example.restfulwebservice.Entity.User;
import com.fasterxml.jackson.annotation.JsonFilter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor  // 어노테이션은 파라미터가 없는 기본 생성자를 생성
//@JsonIgnoreProperties(value={"password", "ssn"})   //  각각 @JsonIgnore 안하고 한번에 적용 가능함.
@JsonFilter("UserInfoV2") // 컨트롤 클래스, 서비스 클래스에서 사용함.
public class UserV2 extends User {
    private String grade;   // 등급

}
