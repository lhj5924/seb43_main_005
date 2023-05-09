package com.firesuits.server.domain.member.entity;

import com.firesuits.server.domain.article.entity.Article;
import com.firesuits.server.domain.article.entity.ArticleComment;
import com.firesuits.server.domain.content.entity.Content;
import com.firesuits.server.global.audit.AuditingFields;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Member extends AuditingFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;
    private String email;
    private String nickName;
    private String password;
    private int experience;
    private int level;
    private int requiredExperience;
    private String profileImage;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Article> articles = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<ArticleComment> articleComments = new ArrayList<>();

    // content 테이블 생성을 위해서 임의로 작성
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Content> contents = new ArrayList<>();

    public static Member of(String email, String nickName, String encodedPwd){
        Member entity = new Member();
        entity.setEmail(email);
        entity.setNickName(nickName);
        entity.setPassword(encodedPwd);
        return entity;
    }
}
