package stu.napls.nabootsocket.model;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "socket_conversation")
@EntityListeners(AuditingEntityListener.class)
@Data
public class Conversation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "type")
    private int type;

    @OneToMany(fetch = FetchType.LAZY)
    @Column(name = "participant")
    private Set<User> users;


    @Column(name = "createDate")
    @CreatedDate
    private Date createDate;

    @Column(name = "updateDate")
    @LastModifiedDate
    private Date updateDate;

}
