package com.bms.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@Access(AccessType.FIELD)
@Entity
@Table(name = "user_rule")
@EqualsAndHashCode(of = {"id"})
public class UserRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private Long userId;

    private Long ruleId;
}
