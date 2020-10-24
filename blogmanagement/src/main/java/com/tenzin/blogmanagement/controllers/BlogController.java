package com.tenzin.blogmanagement.controllers;

import com.tenzin.blogmanagement.daos.BlogDao;
import com.tenzin.blogmanagement.daos.UserDao;
import com.tenzin.blogmanagement.dtos.Blog;
import com.tenzin.blogmanagement.dtos.User;
import java.time.LocalDate;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author Tenzin Woesel Oct 21, 2020
 */
@Controller
public class BlogController {

    @Autowired
    BlogDao blogDao;

    @Autowired
    UserDao userDao;

    @GetMapping("/blog")
    public String displayContentPage() {
        return "blog";
    }

    @GetMapping("/createBlog")
    public String createBlog() {
        return "createBlog";
    }

    @PostMapping("/createBlog")
    public String createBlog(HttpServletRequest request, Blog blog) {

        User user = userDao.getUserById(1);

//        LocalDate date = LocalDate.parse(request.getParameter("expiryDate"));

        blog.setBlogPosted(LocalDate.now());
        blog.setExpiryDate(LocalDate.now());
        blog.setBlogCreated(LocalDate.now().minusDays(2));
        blog.setPhotoFileName("C://");
        blog.setUser(user);

        blogDao.addBlog(blog);

        return "redirect:/blog";
    }
}
