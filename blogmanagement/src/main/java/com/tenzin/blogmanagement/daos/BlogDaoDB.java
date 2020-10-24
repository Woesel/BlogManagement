package com.tenzin.blogmanagement.daos;

import com.tenzin.blogmanagement.daos.HashtagDaoDB.HashtagMapper;
import com.tenzin.blogmanagement.daos.RoleDaoDB.RoleMapper;
import com.tenzin.blogmanagement.daos.UserDaoDB.UserMapper;
import com.tenzin.blogmanagement.dtos.Blog;
import com.tenzin.blogmanagement.dtos.Hashtag;
import com.tenzin.blogmanagement.dtos.Role;
import com.tenzin.blogmanagement.dtos.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Tenzin Woesel Oct 18, 2020
 */
@Repository
public class BlogDaoDB implements BlogDao {

    @Autowired
    JdbcTemplate jdbc;

    @Override
    public Blog getBlogById(int blogId) {
        try {
            final String SELECT_BLOG_BY_ID = "SELECT * FROM Blog WHERE blogId=?";

            Blog blog = jdbc.queryForObject(SELECT_BLOG_BY_ID, new BlogMapper(), blogId);

            blog.setUser(getUserForBlog(blog.getBlogId()));
            blog.setTags(getHashtagsForBlog(blog.getBlogId()));

            return blog;
        } catch (DataAccessException ex) {
            return null;
        }
    }

    @Override
    public Blog getBlogByTitle(String title) {
        try {
            final String SELECT_BLOG_BY_TITLE = "SELECT * FROM Blog WHERE title=?";
            Blog blog = jdbc.queryForObject(SELECT_BLOG_BY_TITLE, new BlogMapper(), title);
            blog.setUser(getUserForBlog(blog.getBlogId()));
            blog.setTags(getHashtagsForBlog(blog.getBlogId()));
            return blog;
        } catch (DataAccessException ex) {
            return null;
        }
    }

    @Override
    @Transactional
    public Blog addBlog(Blog blog) {
        final String INSERT_BLOG = "INSERT INTO Blog (title, content, verified, staticPage, blogPosted, expiryDate, blogCreated, photoFileName, userId)"
                + " VALUES (?,?,?,?,?,?,?,?,?)";
        jdbc.update(INSERT_BLOG,
                blog.getTitle(),
                blog.getContent(),
                blog.isVerified(),
                blog.isStaticPage(),
                blog.getBlogPosted(),
                blog.getExpiryDate(),
                blog.getBlogCreated(),
                blog.getPhotoFileName(),
                blog.getUser().getUserId());

        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        blog.setBlogId(newId);

        if (blog.getTags() != null) {

            insertHashtag(blog);
        }

        return blog;

    }

    @Override
    public List<Blog> getAllBlogs() {
        final String SELECT_ALL_BLOGS = "SELECT * FROM Blog";
        List<Blog> blogs = jdbc.query(SELECT_ALL_BLOGS, new BlogMapper());

        associateTag(blogs);

        return blogs;
    }

    @Override
    public void deleteBlog(int blogId) {
        final String DELETE_BLOG_HASHTAG = "DELETE FROM Blog_Hashtag WHERE blogId=?";
        final String DELETE_BLOG = "DELETE FROM Blog WHERE blogId=?";

        jdbc.update(DELETE_BLOG_HASHTAG, blogId);
        jdbc.update(DELETE_BLOG, blogId);

    }

    @Override
    public void updateBlog(Blog blog) {
        final String UPDATE_BLOG = "UPDATE Blog SET title=?, content=?, verified=?, staticPage=?, blogPosted=?, expiryDate=?, blogCreated=?, photoFileName=?, userId=? WHERE blogId=?";
        jdbc.update(UPDATE_BLOG,
                blog.getTitle(),
                blog.getContent(),
                blog.isVerified(),
                blog.isStaticPage(),
                blog.getBlogPosted(),
                blog.getExpiryDate(),
                blog.getBlogCreated(),
                blog.getPhotoFileName(),
                blog.getUser().getUserId(),
                blog.getBlogId());

        final String DELETE_BLOG_HASHTAG = "DELETE FROM Blog_Hashtag WHERE blogId=? ";
        jdbc.update(DELETE_BLOG_HASHTAG, blog.getBlogId());
        insertHashtag(blog);

    }

    /////////////////////////////////////////////////////////////
    //////////HELPER METHODS
    private List<Role> getRolesForUser(int userId) {
        final String SELECT_ROLE_FOR_USER = " SELECT r.* from Role r JOIN User_Role ur ON r.roleId=ur.roleId WHERE ur.userId=?";
        return jdbc.query(SELECT_ROLE_FOR_USER, new RoleMapper(), userId);
    }

    private User getUserForBlog(int blogId) {
        final String SELECT_USER_FOR_BLOG = " SELECT u.* FROM User u JOIN blog b ON b.userId = u.userId WHERE b.blogId=?";
        User user = jdbc.queryForObject(SELECT_USER_FOR_BLOG, new UserMapper(), blogId);
        user.setRoles(getRolesForUser(user.getUserId()));

        return user;
    }

    private void insertHashtag(Blog blog) {
        final String INSERT_BLOG_HASHTAG = "INSERT INTO Blog_Hashtag(blogId, hashtagId) VALUES (?,?)";
        for (Hashtag tag : blog.getTags()) {
            jdbc.update(INSERT_BLOG_HASHTAG, blog.getBlogId(), tag.getHashtagId());
        }
    }

    private List<Hashtag> getHashtagsForBlog(int blogId) throws DataAccessException {

//        final String SELECT_TAGS_FOR_BLOG = "SELECT h.* from Blog_Hashtag bh JOIN Hashtag h ON bh.hashtagId = h.hashtagId WHERE blogId=? ";
        final String SELECT_TAGS_FOR_BLOG = "SELECT h.* from Hashtag h JOIN Blog_Hashtag bh  ON bh.hashtagId = h.hashtagId WHERE bh.blogId=? ";
        List<Hashtag> tags = jdbc.query(SELECT_TAGS_FOR_BLOG, new HashtagMapper(), blogId);
        return tags;

    }

    private void associateTag(List<Blog> blogs) {

        for (Blog blog : blogs) {
            blog.setUser(getUserForBlog(blog.getBlogId()));
            blog.setTags(getHashtagsForBlog(blog.getBlogId()));
            if (blog.getTags().isEmpty()) {
                blog.setTags(null);
            }

        }
    }

    /////////////////////////////////////////////////////////////
    ////////////MAPPER
    public static final class BlogMapper implements RowMapper<Blog> {

        @Override
        public Blog mapRow(ResultSet rs, int i) throws SQLException {
            Blog blog = new Blog();
            blog.setBlogId(rs.getInt("blogId"));
            blog.setTitle(rs.getString("title"));
            blog.setContent(rs.getString("content"));
            blog.setVerified(rs.getBoolean("verified"));
            blog.setStaticPage(rs.getBoolean("staticPage"));
            blog.setBlogPosted(rs.getDate("blogPosted").toLocalDate());
            if (rs.getTimestamp("expiryDate") != null) {
                blog.setExpiryDate(rs.getDate("expiryDate").toLocalDate());

            }
            blog.setBlogCreated(rs.getDate("blogCreated").toLocalDate());
            blog.setPhotoFileName(rs.getString("photoFileName"));

            return blog;
        }
    }

}
