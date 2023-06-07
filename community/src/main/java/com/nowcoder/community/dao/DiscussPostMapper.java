package com.nowcoder.community.dao;

import com.nowcoder.community.entity.DiscussPost;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiscussPostMapper {
    //分页查询帖子
    //动态SQL，有时要拼接id，有时不需要拼接
    //offset: 起始行， limit：每页的条数
    List<DiscussPost> selectDiscussPosts(int userId, int offset, int limit);

    //查询总共有多少帖子
    //param用来给参数取别名，
    //如果这个方法只有一个参数时，并且在<if>使用，必须要写@param
    int selectDiscussPostRows(@Param("userId") int userId);

}
