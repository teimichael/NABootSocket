package stu.napls.nabootsocket.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "socket_user")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "uuid")
    private String uuid;

    @Column(name = "sessionId")
    private String sessionId;

    @Column(name = "createDate")
    private Date createDate;

    @Column(name = "status")
    private int status;
}
