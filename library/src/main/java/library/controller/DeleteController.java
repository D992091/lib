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
public class DeleteController {
    @Autowired
    BooksRepo booksRepo;

    @GetMapping("/delete")
    public String delete(Map<String,Object> model){
        model.put("error", "");
        return "delete";
    }

    @PostMapping("/deleted")
    public String delete(String id, Map<String,Object> model){
        if(!(booksRepo.findAllById(Integer.valueOf(id)).size() == 0) && id.matches("[0-9]+")) {
            booksRepo.deleteById(Integer.valueOf(id));
            return "redirect:/";
        }else{
            model.put("error","Неверно введён номер книги");
            return "delete";
        }

    }
}
