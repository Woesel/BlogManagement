
package com.tenzin.blogmanagement.daos;

import com.tenzin.blogmanagement.dtos.Blog;
import java.util.List;

/**
 *
 * @author Tenzin Woesel
 * Oct 18, 2020
 */
public class BlogDaoDB implements BlogDao{

    @Override
    public Blog getBlogById(int blogId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Blog getBlogByTitle(String title) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Blog addBlog(Blog blog) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Blog> getAllBlogs(Blog blog) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteBlog(int blogId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateBlog(int blogId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
