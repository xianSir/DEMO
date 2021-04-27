package com.xks.mybatisplus.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xks.mybatisplus.entity.User;
import org.springframework.stereotype.Repository;


/**
 * @author xks
 * @date 2019-10-25
 */
@Repository
public interface UserDao extends BaseMapper<User> {
}
