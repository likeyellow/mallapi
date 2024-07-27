package org.zerock.mallapi.controller.kafka;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ErrorMessage {
    
    private Date timestamp;
    private String message;
    private String details;

}
