package com.oracle.springbootcache.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.oracle.springbootcache.entity.Book;
import org.springframework.stereotype.Repository;

@Repository
public interface BookMapper extends BaseMapper<Book> {
}
