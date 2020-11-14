package library.controller;

import library.model.Books;
import library.repository.BooksRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class Main {
    @Autowired
    BooksRepo booksRepo;

    @GetMapping("/")
    public String greeting(Map<String,Object> model){
        List<Books> books = booksRepo.findAll();
        model.put("books",books);
        return "index";
    }

    @GetMapping("/letter")
    public String getByFirstLetter(Map<String,Object> model, @RequestParam("letter") String letter){
        List<Books> books = booksRepo.findByNameStartsWith(letter);
        model.put("books",books);
        return "index";
    }

}
