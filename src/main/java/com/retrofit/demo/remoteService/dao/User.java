package com.retrofit.demo.remoteService.dao;

import lombok.*;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {
    private String id;
    private int age;
    private String gender;
    private String name;
}
