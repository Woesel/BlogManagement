package com.tenzin.blogmanagement.dtos;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 *
 * @author Tenzin Woesel Oct 17, 2020
 */
public class Blog {

    private int blogId;
    private String title;
    private String content;
    private boolean verified;
    private boolean staticPage;
    private LocalDateTime blogPosted;
    private LocalDateTime expiryDate;
    private String photoFileName;
    private User user;

    public int getBlogId() {
        return blogId;
    }

    public void setBlogId(int blogId) {
        this.blogId = blogId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public boolean isStaticPage() {
        return staticPage;
    }

    public void setStaticPage(boolean staticPage) {
        this.staticPage = staticPage;
    }

    public LocalDateTime getBlogPosted() {
        return blogPosted;
    }

    public void setBlogPosted(LocalDateTime blogPosted) {
        this.blogPosted = blogPosted;
    }

    public LocalDateTime getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDateTime expiryDate) {
        this.expiryDate = expiryDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + this.blogId;
        hash = 83 * hash + Objects.hashCode(this.title);
        hash = 83 * hash + Objects.hashCode(this.content);
        hash = 83 * hash + (this.verified ? 1 : 0);
        hash = 83 * hash + (this.staticPage ? 1 : 0);
        hash = 83 * hash + Objects.hashCode(this.blogPosted);
        hash = 83 * hash + Objects.hashCode(this.expiryDate);
        hash = 83 * hash + Objects.hashCode(this.user);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Blog other = (Blog) obj;
        if (this.blogId != other.blogId) {
            return false;
        }
        if (this.verified != other.verified) {
            return false;
        }
        if (this.staticPage != other.staticPage) {
            return false;
        }
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        if (!Objects.equals(this.content, other.content)) {
            return false;
        }
        if (!Objects.equals(this.blogPosted, other.blogPosted)) {
            return false;
        }
        if (!Objects.equals(this.expiryDate, other.expiryDate)) {
            return false;
        }
        if (!Objects.equals(this.user, other.user)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Blog{" + "blogId=" + blogId + ", title=" + title + ", content=" + content + ", verified=" + verified + ", staticPage=" + staticPage + ", blogPosted=" + blogPosted + ", expiryDate=" + expiryDate + ", user=" + user + '}';
    }

}
