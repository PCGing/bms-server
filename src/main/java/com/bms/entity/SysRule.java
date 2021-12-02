package com.bms.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@Access(AccessType.FIELD)
@Entity
@Table(name = "sys_rule")
@EqualsAndHashCode(of = {"id"})
public class SysRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String name;
}
