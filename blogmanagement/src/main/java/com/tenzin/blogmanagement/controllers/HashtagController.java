package com.tenzin.blogmanagement.controllers;

import com.tenzin.blogmanagement.daos.HashtagDao;
import com.tenzin.blogmanagement.dtos.Hashtag;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author Tenzin Woesel Oct 24, 2020
 */
@Controller
public class HashtagController {

    @Autowired
    HashtagDao hashtagDao;

    Set<ConstraintViolation<Hashtag>> violations = new HashSet<>();

    @GetMapping("/hashtag")
    public String getHashtag(Model model) {

        List<Hashtag> tags = hashtagDao.getAllHashtags();

        model.addAttribute("tags", tags);
//        model.addAttribute("errors", violations);
        return "hashtag";
    }

    @PostMapping("addHashtag")
    public String addHashtag(String name) {
        Hashtag hashtag = new Hashtag();
        hashtag.setName(name);
//        Validator validate = Validation.buildDefaultValidatorFactory().getValidator();
//        violations = validate.validate(hashtag);
//
//        if (violations.isEmpty()) {
//            hashtagDao.addHashtag(hashtag);
//        }
        hashtagDao.addHashtag(hashtag);
        
        return "redirect:/createBlog";
    }

    @GetMapping("deleteHashtag")
    public String deleteHashtag(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));

        hashtagDao.deleteHashtag(id);
        return "redirect:/hashtag";
    }

}
