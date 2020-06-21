package site.dunhanson.email.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author dunhanson
 * 2020-06-21
 * SMTP
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Smtp {
    private String host;
    private Integer port;
    private Boolean ssl;
}
