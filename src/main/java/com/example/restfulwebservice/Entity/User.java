package com.example.restfulwebservice.Entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.OneToMany;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor  // 어노테이션은 파라미터가 없는 기본 생성자를 생성
//@JsonIgnoreProperties(value={"password", "ssn"})   //  각각 @JsonIgnore 안하고 한번에 적용 가능함.
//@JsonFilter("UserInfo") // 컨트롤 클래스, 서비스 클래스에서 사용함.
@ApiModel(description = "사용자 상세 정보를 위한 도메인 객체")    // swagger api
@Entity
public class User {

    @Id
    @GeneratedValue
    private Integer id;

    @Size(min=2, message = "Name은 2글자 이상 입력해 주세요.")
    @ApiModelProperty(notes = "사용자 이름을 입력해 주세요.")    // swagger api
    private String name;

    @Past
    @ApiModelProperty(notes = "사용자 등록일을 입력해 주세요.")
    private Date joinDate;


    //@JsonIgnore     // 전달하고자 하는 데이터 무시
    @ApiModelProperty(notes = "사용자 패스워드을 입력해 주세요.")
    private String password;

    //@JsonIgnore
    @ApiModelProperty(notes = "사용자 주민번호을 입력해 주세요.")
    private String ssn;



    @OneToMany(mappedBy = "user")
    private List<Post> posts;

    public User(int id, String name, Date joinDate, String password, String ssn) {
        this.id = id;
        this.name = name;
        this.joinDate = joinDate;
        this.password = password;
        this.ssn = ssn;
    }

}
