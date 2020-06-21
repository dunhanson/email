package site.dunhanson.email.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author dunhanson
 * 2020-06-21
 * 发送接收
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FromTo {
    private Sender from;
    private Receiver to;
}
