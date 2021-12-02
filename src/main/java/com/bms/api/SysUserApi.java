package com.bms.api;

import com.bms.entity.SysUser;
import com.bms.repository.SysUserRepository;
import com.bms.utils.RedisUtil;
import com.bms.utils.ResultObj;
import com.bms.utils.Util;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Api(value = "用户相关接口")
@Slf4j
@RequestMapping("/sysUser")
public class SysUserApi {

    @Autowired
    private SysUserRepository sysUserRepository;

    @Resource
    private RedisUtil redisUtil;

    @ApiOperation(value = "查询所有用户",tags = {"用户与权限相关接口"})
    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public ResultObj findAll() {
        List<SysUser> users = sysUserRepository.findAll();
        return new ResultObj(users);
    }

    @ApiOperation(value = "根据Id查询用户",tags = {"用户与权限相关接口"})
    @RequestMapping(value = "/findById", method = RequestMethod.GET)
    public ResultObj findById(Long id) {
        try {
            SysUser user = sysUserRepository.findById(id).get();
            return new ResultObj(user);
        }catch (Exception e){
            log.debug(e.getMessage());
            return new ResultObj(false,"未查询到该用户");
        }
    }

    @ApiOperation(value = "根据账户查询用户",tags = {"用户与权限相关接口"})
    @RequestMapping(value = "/findByUsername", method = RequestMethod.GET)
    public ResultObj findByUsername(String username) {
        List<SysUser> users = sysUserRepository.findByUsername(username);
        if (users!=null&&users.size()>0){
            return new ResultObj(users.get(0));
        }
        return new ResultObj(false,"未查询到该用户");
    }

    @ApiOperation(value = "新增/修改用户",tags = {"用户与权限相关接口"})
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResultObj save(SysUser user) {
        Long id = user.getId();
        //新增
        if(Util.isEmpty(id)){
            List<SysUser> users = sysUserRepository.findByUsername(user.getUsername());
            if(Util.isEmpty(users)){
                SysUser sysUser = sysUserRepository.save(user);
                return new ResultObj(sysUser);
            }else{
                return new ResultObj(false, "该账户已存在");
            }
        }else{//修改
            if(Util.beanIsEmpty(user)){
                return new ResultObj(false, "不能有空值");
            }else {
                SysUser save = sysUserRepository.save(user);
                return new ResultObj(save);
            }
        }
    }

    @ApiOperation(value = "测试redis",tags = {"用户与权限相关接口"})
    @RequestMapping(value = "/testRedis", method = RequestMethod.GET)
    public ResultObj testRedis(String key, String value) {
        redisUtil.set(key,value);
        return new ResultObj(redisUtil.get(key));
    }
}
