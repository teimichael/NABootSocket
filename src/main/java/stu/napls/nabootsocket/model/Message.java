package stu.napls.nabootsocket.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "socket_message")
@Data
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "sender")
    private String sender;

    @Column(name = "receiver")
    private String receiver;

    @Column(name = "content")
    private String content;

    @Column(name = "timestamp")
    private Long timestamp;

}
