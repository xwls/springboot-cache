package com.oracle.springbootcache.controoler;

import com.baomidou.mybatisplus.extension.api.R;
import com.oracle.springbootcache.entity.Book;
import com.oracle.springbootcache.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/books")
    public R<List<Book>> books(){
        List<Book> list = bookService.list();
        return R.ok(list);
    }

    //    @GetMapping("/book?bookId=102")
    //使用路径传参
    @GetMapping("/book/{bookId}")
    public R<Book> book(@PathVariable Integer bookId){
        Book book = bookService.selectOne(bookId);
        return R.ok(book);
    }

    @PostMapping("/book")
    public R<Book> save(Book book){
        Book save = bookService.save(book);
        return R.ok(save);
    }

    @PutMapping("/book")
    public R<Book> update(Book book){
        bookService.update(book);
        return R.ok(book);
    }

    @DeleteMapping("/book/{bookId}")
    public R<String> delete(@PathVariable Integer bookId){
        boolean remove = bookService.delete(bookId);
        return remove ? R.ok("删除成功") : R.failed("删除失败");
    }
}
