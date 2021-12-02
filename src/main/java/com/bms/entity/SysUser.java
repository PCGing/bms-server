package com.bms.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@Access(AccessType.FIELD)
@Entity
@Table(name = "sys_user")
@EqualsAndHashCode(of = {"id"})
public class SysUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String name;

    private String username;

    private String password;

}
