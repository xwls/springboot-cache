package com.oracle.springbootcache.service;

import com.oracle.springbootcache.entity.Book;

import java.util.List;

public interface BookService {


    /**
     * 查询全部图书
     * @return
     */
    List<Book> list();

    /**
     * 根据编号查询
     * @param bookId
     * @return
     */
    Book selectOne(Integer bookId);

    /**
     * 添加图书
     * @param book
     * @return
     */
    Book save(Book book);

    /**
     * 修改图书
     * @param book
     * @return
     */
    Book update(Book book);

    /**
     * 删除图书
     * @param bookId 编号
     * @return
     */
    boolean delete(Integer bookId);

}
