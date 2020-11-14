package library.repository;

import library.model.Books;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface BooksRepo extends JpaRepository<Books,Integer> {

List<Books> findAll();

List<Books> findByNameStartsWith(String letter);
void deleteById(@Param("id")Integer id);
List<Books> findAllById(@Param("id")Integer id);
List<Books> findAllByAuthor(@Param("author")String author);
List<Books> findAllByName(@Param("name")String author);
List<Books> findAllByArticul(@Param("articul")String articul);

@Modifying
@Transactional
@Query(value = "update books b set b.name = ?1, b.author = ?2, b.articul = ?3 where b.id = ?4" , nativeQuery = true)
void changeBooks(String name, String author, String articul, Integer id);
}
