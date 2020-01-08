package stu.napls.nabootsocket.model;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import stu.napls.nabootsocket.core.dictionary.StatusCode;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "socket_user")
@EntityListeners(AuditingEntityListener.class)
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "uuid", nullable = false, unique = true)
    private String uuid;

    @Column(name = "sessionId")
    private String sessionId;

    @Column(name = "unreadList")
    private String unreadList;

    @Column(name = "createDate")
    @CreatedDate
    private Date createDate;

    @Column(name = "updateDate")
    @LastModifiedDate
    private Date updateDate;

    @Column(name = "status", columnDefinition = "integer default " + StatusCode.NORMAL)
    private int status;
}
