package com.tenzin.blogmanagement.controllers;

import com.tenzin.blogmanagement.daos.BlogDao;
import com.tenzin.blogmanagement.daos.BlogDaoDB;
import com.tenzin.blogmanagement.dtos.Blog;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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

    @GetMapping({"/", "/home"})
    public String displayHomePage(Model model) {

        List<Blog> blogs = blogDao.getAllBlogs();
        List<Blog> verifiedBlogs = new ArrayList<>();

        try {
            for (Blog blog : blogs) {
                if (blog.isVerified()) {
                    verifiedBlogs.add(blog);
                }

            }

        } catch (Exception e) {
            //do nothing
        }

//        model.addAttribute("blogs", blogs);
        model.addAttribute("blogs", verifiedBlogs);

        return "home";
    }

//    @GetMapping("searchResult")
//    public String searchByHashtag(HttpServletRequest request, Model model) {
//        
//        int id = Integer.parseInt(request.getParameter("hashtagId"));
//
//        List<Blog> blogsByHashtag = blogDB.getBlogsByHashtag(id);
////        List<Blog> allBlogs = blogDao.getAllBlogs();
////
////        for (Blog allBlog : allBlogs) {
////            if (allBlog.getTags().contains(hashtagDao.getHashtagById(id))) {
////                blogsByHashtag.add(allBlog);
////            }
////        }
//
//        model.addAttribute("blogs", blogsByHashtag);
//
//        return "searchResult";
//
//    }
}
