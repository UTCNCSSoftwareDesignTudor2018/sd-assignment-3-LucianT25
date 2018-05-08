package com.utcn.Articles.Communication;

import com.google.gson.Gson;
import com.utcn.Articles.Persistence.Entity.Article;
import com.utcn.Articles.Persistence.Entity.Writer;
import com.utcn.Articles.Service.WriterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class WriterController {

    WriterService writerService;

    @Autowired
    WriterController(WriterService writerService) {
        this.writerService = writerService;
    }

   // @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    String login(@RequestBody String credentials) {
        Gson gson = new Gson();
        LoginCredentials loginCredentials = gson.fromJson(credentials, LoginCredentials.class);
        System.out.println("Received login request"+ loginCredentials.getUsername()+" / "+loginCredentials.getPassword());
        Writer writer = writerService.login(loginCredentials.getUsername(), loginCredentials.getPassword());
        if(writer != null) {
            return "{\"destination\":\"/writer/"+writer.getUsername()+"\"}";
        }
        return "{\"destination\":\"/login\"}";
    }

}
