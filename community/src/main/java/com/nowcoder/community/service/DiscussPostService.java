package com.nowcoder.community.service;

import com.nowcoder.community.dao.DiscussPostMapper;
import com.nowcoder.community.entity.DiscussPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiscussPostService {
    @Autowired
    private DiscussPostMapper discussPostMapper;

    public List<DiscussPost> findDiscussPosts(int userId, int offset, int limit)
    {
        //直接调用dao层的查询，到数据库中查询每页的内容
        //offeset:起始位置
        //limit:每页展示多少条数据
        return discussPostMapper.selectDiscussPosts(userId, offset, limit);
    }

    public int findDiscusspostRows(int userId){

        return discussPostMapper.selectDiscussPostRows(userId);
    }
}
