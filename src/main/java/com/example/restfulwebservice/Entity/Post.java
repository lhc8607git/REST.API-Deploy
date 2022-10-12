package com.example.restfulwebservice.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue   // 자동생성
    private Integer id;

    private String description;
                                     // User  Post
    // User : Post -> 1 : (0~N),        Main : Sub -> Parent : Child
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore    // 웹으로 공개할 때 표시 안함.
    private User user;
}
