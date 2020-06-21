package site.dunhanson.email.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author dunhanson
 * 2020-06-21
 * 发送结果
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Result {
    public Boolean success;
    public String message;
}
