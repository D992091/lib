package library.controller;

import library.model.Books;
import library.repository.BooksRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Map;

@Controller
public class FindController {
    @Autowired
    BooksRepo booksRepo;

    @GetMapping("/find")
    public String find(Map<String,Object> model){
        model.put("error", "");
        return "find";
    }

    @PostMapping("/search")
    public String search(String type, String value, Map<String,Object> model){
        if(type.equals("Название книги")){
            List<Books> books = booksRepo.findAllByName(value);
            model.put("books", books);
            model.put("error", "");

        }
        if(type.equals("Автор")){
            List<Books> books = booksRepo.findAllByAuthor(value);
            model.put("books", books);
            model.put("error", "");
        }
        if(type.equals("Артикул")){
            List<Books> books = booksRepo.findAllByArticul(value);
            model.put("books", books);
            model.put("error", "");
        }
        return "find";
    }
}
