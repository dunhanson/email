package site.dunhanson.email.entity;

import lombok.Data;

/**
 * @author dunhanson
 * 2020-06-21
 * 邮件发送者
 */
@Data
public class Sender {
    private String name;
    private Authenticator authenticator;
    private Smtp smtp;
}
