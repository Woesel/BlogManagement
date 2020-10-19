/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tenzin.blogmanagement.daos;

import com.tenzin.blogmanagement.dtos.Blog;
import java.util.List;

/**
 *
 * @author Tenzin Woesel
 */
public interface BlogDao {

    Blog getBlogById(int blogId);

    Blog getBlogByTitle(String title);

    Blog addBlog(Blog blog);

    List<Blog> getAllBlogs(Blog blog);

    void deleteBlog(int blogId);

    void updateBlog(int blogId);
}
