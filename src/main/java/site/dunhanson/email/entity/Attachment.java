package site.dunhanson.email.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author dunhanson
 * 2020-06-21
 * 附件
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Attachment {
    private String pathOrUrl;
    private String name;
    private String description;
}
