package com.tenzin.blogmanagement.dtos;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 *
 * @author Tenzin Woesel Oct 17, 2020
 */
public class Blog {

    private int blogId;
    @NotBlank(message = "Title must not be empty.")
    @Size(max = 45, message = "Title must be less than 45 characters.")
    private String title;
    @NotBlank(message = "Content must not be empty.")
    private String content;
    private boolean verified;
    private boolean staticPage;
    private LocalDate blogCreated;
    private LocalDate blogPosted;
    private LocalDate expiryDate;
    private String photoFileName;
    private User user;
    private List<Hashtag> tags;

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

    public LocalDate getBlogCreated() {
        return blogCreated;
    }

    public void setBlogCreated(LocalDate blogCreated) {
        this.blogCreated = blogCreated;
    }

    public LocalDate getBlogPosted() {
        return blogPosted;
    }

    public void setBlogPosted(LocalDate blogPosted) {
        this.blogPosted = blogPosted;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getPhotoFileName() {
        return photoFileName;
    }

    public void setPhotoFileName(String photoFileName) {
        this.photoFileName = photoFileName;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Hashtag> getTags() {
        return tags;
    }

    public void setTags(List<Hashtag> tags) {
        this.tags = tags;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.blogId;
        hash = 59 * hash + Objects.hashCode(this.title);
        hash = 59 * hash + Objects.hashCode(this.content);
        hash = 59 * hash + (this.verified ? 1 : 0);
        hash = 59 * hash + (this.staticPage ? 1 : 0);
        hash = 59 * hash + Objects.hashCode(this.blogCreated);
        hash = 59 * hash + Objects.hashCode(this.blogPosted);
        hash = 59 * hash + Objects.hashCode(this.expiryDate);
        hash = 59 * hash + Objects.hashCode(this.photoFileName);
        hash = 59 * hash + Objects.hashCode(this.user);
        hash = 59 * hash + Objects.hashCode(this.tags);
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
        if (!Objects.equals(this.photoFileName, other.photoFileName)) {
            return false;
        }
        if (!Objects.equals(this.blogCreated, other.blogCreated)) {
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
        if (!Objects.equals(this.tags, other.tags)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Blog{" + "blogId=" + blogId + ", title=" + title + ", content=" + content + ", verified=" + verified + ", staticPage=" + staticPage + ", blogCreated=" + blogCreated + ", blogPosted=" + blogPosted + ", expiryDate=" + expiryDate + ", photoFileName=" + photoFileName + ", user=" + user + ", tags=" + tags + '}';
    }

}
