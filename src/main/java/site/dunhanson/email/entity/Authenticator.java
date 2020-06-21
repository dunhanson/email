package site.dunhanson.email.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author dunhanson
 * 2020-06-21
 * 认证
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Authenticator {
    private String username;
    private String password;
}
