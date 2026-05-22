
// @Repository
// public class BookRepository {
//     private final Map<Long, Book> books = new HashMap<>();
//     private final AtomicLong idCounter = new AtomicLong(1);

//     public BookRepository() {
//         // 初期データ
//         books.put(1L, new Book(1L, "Spring入門", "太郎", "978-1234567890", 3000.0));
//         books.put(2L, new Book(2L, "Java完全ガイド", "花子", "978-0987654321", 4500.0));
//     }

//     public List<Book> findAll() {
//         return new ArrayList<>(books.values());
//     }

//     public Optional<Book> findById(Long id) {
//         return Optional.ofNullable(books.get(id));
//     }

//     public Book save(Book book) {
//         if (book.getId() == null) {
//             book.setId(idCounter.getAndIncrement());
//         }
//         books.put(book.getId(), book);
//         return book;
//     }

//     public void deleteById(Long id) {
//         books.remove(id);
//     }
// }

package com.example.book_management.repository;
 
import com.example.book_management.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Optional;
 
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    // エラーに出ていた List や Optional, Query を使うための import を上に追加しました
    Optional<Book> findByTitle(String title);
    List<Book> findByAuthor(String author);
    List<Book> findByTitleContaining(String keyword);
    @Query("SELECT b FROM Book b WHERE b.title LIKE %:keyword% OR b.author LIKE %:keyword%")
    List<Book> searchByKeyword(@Param("keyword") String keyword);
}