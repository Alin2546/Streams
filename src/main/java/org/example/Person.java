package org.example;

import lombok.AllArgsConstructor;
import lombok.Data;



@Data
@AllArgsConstructor
public class Person {
    private String firstName;
    private String lastName;
    private String date;

}
