package com.bms.repository;

import com.bms.entity.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SysUserRepository extends JpaRepository<SysUser, Long> {

    /**
     * 根据账号查询用户
     * @param username
     * @return
     */
    @Query(value = "select * from sys_user where username = ?1", nativeQuery = true)
    List<SysUser> findByUsername(String username);
}
