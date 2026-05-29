package com.example.book_management.service;

import com.example.book_management.model.Book;
import com.example.book_management.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    // 全ての書籍を取得する
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    // IDで書籍を1件取得する
    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }

    // 新しい書籍を登録する
    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    // 書籍の情報を更新する
    public Book updateBook(Long id, Book bookDetails) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("指定された本が見つかりません。ID: " + id));
        
        book.setTitle(bookDetails.getTitle());
        book.setAuthor(bookDetails.getAuthor());
        book.setIsbn(bookDetails.getIsbn());
        book.setPrice(bookDetails.getPrice());
        
        return bookRepository.save(book);
    }

    // 書籍を削除する
    public void deleteBook(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("指定された本が見つかりません。ID: " + id));
        bookRepository.delete(book);
    }
}