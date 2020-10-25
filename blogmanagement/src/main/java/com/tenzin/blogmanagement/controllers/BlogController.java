package com.tenzin.blogmanagement.controllers;

import com.tenzin.blogmanagement.daos.BlogDao;
import com.tenzin.blogmanagement.daos.BlogDaoDB;
import com.tenzin.blogmanagement.daos.HashtagDao;
import com.tenzin.blogmanagement.daos.UserDao;
import com.tenzin.blogmanagement.dtos.Blog;
import com.tenzin.blogmanagement.dtos.Hashtag;
import com.tenzin.blogmanagement.dtos.User;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    BlogDaoDB blogDB;
    
    @Autowired
    UserDao userDao;
    
    @Autowired
    HashtagDao hashtagDao;
    
    @GetMapping("/blog")
    public String displayContentPage(Model model) {
        
        List<Blog> blogs = blogDao.getAllBlogs();
        
        model.addAttribute("blogs", blogs);
        
        return "blog";
    }
    
    @GetMapping("/createBlog")
    public String createBlog(Model model) {
        
        List<Hashtag> hashtags = hashtagDao.getAllHashtags();
        
        model.addAttribute("hashtags", hashtags);
        
        return "createBlog";
    }
    
    @PostMapping("/createBlog")
    public String createBlog(HttpServletRequest request) {
        
        String[] hashtagIds = request.getParameterValues("hashtagId");
        
        List<Hashtag> hashtags = new ArrayList<>();
        if (hashtagIds != null) {
            for (String hashtagId : hashtagIds) {
                hashtags.add(hashtagDao.getHashtagById(Integer.parseInt(hashtagId)));
            }
        }
        
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String userName = auth.getName();
        User user = userDao.getUserByUsername(userName);
        
        LocalDate date;
        String expiryDate = request.getParameter("expiryDate");
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        
        try {
            date = LocalDate.parse(expiryDate);
        } catch (Exception e) {
            date = null;
        }
        Blog blog = new Blog();
//        LocalDate date = LocalDate.parse(request.getParameter("expiryDate"));
        blog.setTitle(title);
        blog.setContent(content);
        blog.setBlogPosted(LocalDate.now().plusYears(1));
        blog.setExpiryDate(date);
        blog.setBlogCreated(LocalDate.now());
        blog.setPhotoFileName("C://");
        blog.setTags(hashtags);
        blog.setVerified(false);
        
        blog.setUser(user);
        
        blogDao.addBlog(blog);
        
        return "redirect:/blog";
    }
    
//    @GetMapping("searchResult")
//    public String searchByHashtag(HttpServletRequest request, Model model) {
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
    
    @GetMapping("editBlog")
    public String editBlog(Integer id, Model model) {
        Blog blog = blogDao.getBlogById(id);
        
        List<Hashtag> hashtags = hashtagDao.getAllHashtags();
        
        model.addAttribute("blog", blog);
        model.addAttribute("hashtags", hashtags);
        
        return "editBlog";
    }
}
