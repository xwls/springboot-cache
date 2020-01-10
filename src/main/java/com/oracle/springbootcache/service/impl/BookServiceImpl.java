package com.oracle.springbootcache.service.impl;

import com.oracle.springbootcache.entity.Book;
import com.oracle.springbootcache.mapper.BookMapper;
import com.oracle.springbootcache.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
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
    cacheNames：缓存名称
    key：缓存的唯一标识
     */
    @Cacheable(cacheNames = "book",key = "'book-'+#bookId")
    public Book selectOne(Integer bookId) {
        return bookMapper.selectById(bookId);
    }

    @Override
    public Book save(Book book) {
        bookMapper.insert(book);
        return book;
    }

    @Override
    public Book update(Book book) {
        bookMapper.updateById(book);
        return book;
    }

    @Override
    public boolean delete(Integer bookId) {
        int i = bookMapper.deleteById(bookId);
        return i == 1;
    }
}
