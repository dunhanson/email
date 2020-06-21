package site.dunhanson.email.entity;

import lombok.Data;

/**
 * @author dunhanson
 * 2020-06-21
 * 邮件接受者
 */
@Data
public class Receiver {
    private String name;
    private String address;
}
