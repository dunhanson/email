package site.dunhanson.email.entity;

import lombok.Data;

/**
 * @author dunhanson
 * 2020-06-21
 * 收发配置
 */
@Data
public class Transceiver {
    /**名称**/
    private String name;
    /**描述**/
    private String description;
    /**发送者**/
    private String from;
    /**接收者**/
    private String to;
}
