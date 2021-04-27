package com.xks.oracle.mapper;
import java.util.List;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.apache.ibatis.annotations.Param;

import com.xks.oracle.entity.TSalaryList;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @Entity com.zxhz.maintain.oracle.entity.TSalaryList
 */
@DS("oracle")
public interface TSalaryListMapper extends BaseMapper<TSalaryList> {

    List<TSalaryList> selectByRemark4(@Param("remark4") String remark4);
}




