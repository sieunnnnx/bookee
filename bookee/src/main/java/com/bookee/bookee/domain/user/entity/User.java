package com.bookee.bookee.domain.user.entity;

import com.bookee.bookee.global.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "users")
public class User extends BaseEntity {

    private String loginId;

    private String password;

    private String nickname;


    /**
     * 사용자 생성자
     *
     * @param loginId 로그인 아이디
     * @param password 비밀번호
     * @param nickname 닉네임
     * @param email 이메일
     */
    protected User(String loginId, String password, String nickname) {
        this.loginId = loginId;
        this.password = password;
        this.nickname = nickname;
    }

    /**
     * 회원가입용 정적 팩토리 메서드
     *
     * @param loginId 로그인 아이디
     * @param password 비밀번호
     * @param nickname 닉네임
     * @return 생성된 User 객체
     */
    public static User signUp(String loginId, String password, String nickname) {
        return new User(loginId, password, nickname);
    }
}
