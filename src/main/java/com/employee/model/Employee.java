package com.employee.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "employees")
public class Employee {
    @Id
    private String id;
    
    private String firstName;
    
    private String lastName;
    
    private String email;
    
    private String department;
    
    private String position;
    
    private Double salary;
    
    private LocalDateTime createdAt;
    
    private LocalDateTime updatedAt;
}
