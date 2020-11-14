package library.controller;

import library.model.Books;
import library.repository.BooksRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;
import java.util.regex.Pattern;

@Controller
public class AddController {
    @Autowired
    BooksRepo booksRepo;

    @GetMapping("/add")
    public String add(Map<String,Object> model){
        model.put("error", "");
        return "add";
    }

    @PostMapping("/added")
    public String add(String name, String author, String articul, Map<String,Object> model){
        if((booksRepo.findAllByArticul(articul).size() == 0) && articul.matches("[0-9]+") && Pattern.matches("^[А-Яа-я\\s]*$",author)&& Pattern.matches("^[А-Яа-я\\s]*$",name)) {
            Books books = new Books();
            books.setArticul(articul);
            books.setAuthor(author);
            books.setName(name);
            booksRepo.save(books);
            return "redirect:/";
        }else{
            model.put("error","Ошибка добавления книги, проверьте введённые данные");
            return "add";
        }
    }
}
