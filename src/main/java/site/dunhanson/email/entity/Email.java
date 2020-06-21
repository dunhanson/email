package site.dunhanson.email.entity;

import lombok.Data;
import java.util.List;

/**
 * @author dunhanson
 * 2020-06-21
 * 邮件信息
 */
@Data
public class Email {
    /**发送者**/
    private List<Sender> from;
    /**接收者**/
    private List<Receiver> to;
    /**收发配置**/
    private List<Transceiver> fromTo;
}
