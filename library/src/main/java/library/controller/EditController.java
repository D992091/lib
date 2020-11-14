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
import java.util.regex.Pattern;

@Controller
public class EditController {
    @Autowired
    BooksRepo booksRepo;

    @GetMapping("/edit" )
    public String edit(Map<String,Object> model, @RequestParam("id") String id){
        if(id != null){
            List<Books> result = booksRepo.findAllById(Integer.valueOf(id));
            model.put("result",result);

        }
        model.put("error", "");
        return "edit";
    }

    @PostMapping("/found")
    public String found(Map<String,Object> model, String id){
        return "redirect:/edit?id="+id;
    }

    @PostMapping("/change")
    public String change(String id, String name, String author, String articul,Map<String,Object> model) {
        if((booksRepo.findAllByArticul(articul).size() == 0) && articul.matches("[0-9]+") && Pattern.matches("^[А-Яа-я\\s]*$",author)&& Pattern.matches("^[А-Яа-я\\s]*$",name)) {
            booksRepo.changeBooks(name, author, articul, Integer.valueOf(id));
            return "redirect:/";
        }else{
            model.put("error", "Ошибка редактирования книги");
            return "edit";
        }
    }
}
