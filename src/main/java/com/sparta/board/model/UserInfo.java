package com.sparta.board.model;

import com.sparta.board.dto.SignupRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor // 기본 생성자를 만들어줍니다.
@Entity
public class UserInfo {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    // unique: 중복 허용 여부 (false 일때 중복 허용)
    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING) //자바 내에서는 Enum, DB전달할 때는 String
    private UserRoleEnum role;

    public UserInfo(String username, String password, UserRoleEnum role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }
}
