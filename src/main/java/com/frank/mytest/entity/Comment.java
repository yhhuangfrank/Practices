package com.frank.mytest.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.List;

@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "comments")
@NamedQueries({
        @NamedQuery(name = "Comment.DeleteById", query = "DELETE FROM Comment c WHERE c.id = :id"),
        @NamedQuery(name = "Comment.DeleteByIds", query = "DELETE FROM Comment c WHERE c.id IN :ids"),
        @NamedQuery(name = "Comment.findAll", query = "SELECT c FROM Comment c"),
        @NamedQuery(name = "Comment.findByIds", query = "SELECT c FROM Comment c WHERE c.id IN :ids"),
        @NamedQuery(name = "Comment.findByContent", query = "SELECT c FROM Comment c WHERE c.content LIKE :content")
})
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "content")
    private String content;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "comment")
//    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}) // 單向一對多
//    @JoinColumn(name = "comment_id")
    @ToString.Exclude
    private List<Reply> replies;

}