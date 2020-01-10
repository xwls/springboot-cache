package com.oracle.springbootcache.service.impl;

import com.oracle.springbootcache.entity.Book;
import com.oracle.springbootcache.mapper.BookMapper;
import com.oracle.springbootcache.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookMapper bookMapper;

    @Override
    public List<Book> list() {
        return bookMapper.selectList(null);
    }

    @Override
    /*
    执行目标方法之前，先查询缓存，如果缓存中有次数据，则直接从缓存获取，如果缓存没有，则执行目标方法进行查询，将查询结果进行缓存
    cacheNames：缓存名称
    key：缓存的唯一标识
     */
    @Cacheable(cacheNames = "book",key = "'book-'+#bookId")
    public Book selectOne(Integer bookId) {
        return bookMapper.selectById(bookId);
    }

    @Override
    @CachePut(cacheNames = "book",key = "'book-'+#result.bookId")
    public Book save(Book book) {
        bookMapper.insert(book);
        return book;
    }

    @Override
    /**
     * 无论缓存中是否包含此数据，都会执行目标方法，将方法的返回值进行缓存
     */
    @CachePut(cacheNames = "book",key = "'book-'+#book.bookId")
    public Book update(Book book) {
        bookMapper.updateById(book);
        return book;
    }

    @Override
    /**
     * 逐出缓存
     */
    @CacheEvict(cacheNames = "book",key = "'book-'+#bookId")
    public boolean delete(Integer bookId) {
        int i = bookMapper.deleteById(bookId);
        return i == 1;
    }
}
