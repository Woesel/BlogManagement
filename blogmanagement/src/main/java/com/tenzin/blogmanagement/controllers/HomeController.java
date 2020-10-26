package com.tenzin.blogmanagement.controllers;

import com.tenzin.blogmanagement.daos.BlogDao;
import com.tenzin.blogmanagement.daos.BlogDaoDB;
import com.tenzin.blogmanagement.daos.HashtagDao;
import com.tenzin.blogmanagement.dtos.Blog;
import com.tenzin.blogmanagement.dtos.Hashtag;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author Tenzin Woesel Oct 21, 2020
 */
@Controller
public class HomeController {

    @Autowired
    BlogDao blogDao;

    @Autowired
    BlogDaoDB blogDB;

    @Autowired
    HashtagDao hashtagDao;

    @GetMapping({"/", "/home"})
    public String displayHomePage(Model model) {

        List<Hashtag> hashtags = hashtagDao.getAllHashtags();

        List<Blog> blogs = blogDao.getAllBlogs();
        List<Blog> verifiedBlogs = new ArrayList<>();

        try {
            for (Blog blog : blogs) {
                if (blog.isVerified() && blog.isStaticPage()) {
                    verifiedBlogs.add(blog);
                }

            }

        } catch (Exception e) {
            //do nothing
        }

//        model.addAttribute("blogs", blogs);
        model.addAttribute("blogs", verifiedBlogs);
        model.addAttribute("hashtags", hashtags);

        return "home";
    }

    @GetMapping("searchResult")
    public String searchResult(HttpServletRequest request, Model model) {

        int id = Integer.parseInt(request.getParameter("id"));

        List<Hashtag> hashtags = hashtagDao.getAllHashtags();

        List<Blog> blogsByHashtag = blogDB.getBlogsByHashtag(id);

        System.out.println(blogsByHashtag);

        model.addAttribute("blogs", blogsByHashtag);
        model.addAttribute("hashtags", hashtags);

        return "searchResult";

    }

}
