package org.zerock.mallapi.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserIPDTO {
    
    private Long id;

    private String ipAddress;

    private LocalDateTime accessTime;
}
