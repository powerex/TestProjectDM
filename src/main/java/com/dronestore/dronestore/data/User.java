package com.dronestore.dronestore.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Users")
public class User {
    @Id
    private String id;
    @Indexed(unique = true, name = "user_email")
    private String email;
    private String name;
}
