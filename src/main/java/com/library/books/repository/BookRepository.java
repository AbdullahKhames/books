package com.library.books.repository;

import com.library.books.repository.entity.Book;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    boolean existsByIsbn(String isbn);
    List<Book> findBooksByAuthorsContainingIgnoreCase(String author);
    Optional<Book> findBookByIsbn(String isbn);
    @Transactional
    @Modifying
    @Query("update Book b set b.authors = :author," +
            "                b.title = :title," +
            "                b.publisher = :publisher," +
            "                b.year = :year," +
            "                b.price = :price " +
            "                 where b.isbn = :isbn"
    )
    int updateBookByIsbn(String isbn,
                                    String author,
                                    String title,
                                    String publisher,
                                    int year,
                                    int price
                                    );
    @Transactional
    //loads by isbn then deletes by id !
    void deleteByIsbn(String isbn);

//    @Transactional
//    @Modifying
//    @Query("delete Book b " +
//            " where b.isbn = :isbn"
//    )
//    void deleteByIsbn(String isbn);
}
