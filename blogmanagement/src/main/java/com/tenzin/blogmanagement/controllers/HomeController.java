
package com.tenzin.blogmanagement.controllers;

import com.tenzin.blogmanagement.daos.BlogDao;
import com.tenzin.blogmanagement.dtos.Blog;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author Tenzin Woesel
 * Oct 21, 2020
 */
@Controller
public class HomeController {
    
    @Autowired
    BlogDao blogDao;

    @GetMapping({"/", "/home"})
    public String displayHomePage(Model model) {
        
        List<Blog> blogs = blogDao.getAllBlogs();
        model.addAttribute("blogs", blogs);
        
        
        return "home";
    }
}
