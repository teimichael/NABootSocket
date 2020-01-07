package stu.napls.nabootsocket.model;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import stu.napls.nabootsocket.core.dictionary.StatusCode;

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

    @Column(name = "type", nullable = false)
    private int type;

    @Column(name = "users")
    private String users;

    @Column(name = "createDate")
    @CreatedDate
    private Date createDate;

    @Column(name = "updateDate")
    @LastModifiedDate
    private Date updateDate;

    @Column(name = "status", columnDefinition = "integer default " + StatusCode.NORMAL)
    private int status;

    @OneToOne
    @JoinColumn(name = "lastMessage", referencedColumnName = "id")
    private Message lastMessage;

}
