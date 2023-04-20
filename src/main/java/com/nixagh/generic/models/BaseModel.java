package com.nixagh.generic.models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document
public class BaseModel {
    @Id
    private String id;
}
