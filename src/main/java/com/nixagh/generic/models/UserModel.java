package com.nixagh.generic.models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "User")
public class UserModel extends BaseModel{
    private String username;
    private String password;
}
