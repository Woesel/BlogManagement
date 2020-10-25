package com.tenzin.blogmanagement.dtos;

import java.util.Objects;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 *
 * @author Tenzin Woesel Oct 17, 2020
 */
public class Hashtag {

    private int hashtagId;
     @NotBlank(message = "Hashtag name must not be empty.")
    @Size(max = 45, message = "Hashtag name must be less than 45 characters.")
    private String name;

    public int getHashtagId() {
        return hashtagId;
    }

    public void setHashtagId(int hashtagId) {
        this.hashtagId = hashtagId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.hashtagId;
        hash = 97 * hash + Objects.hashCode(this.name);
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
        final Hashtag other = (Hashtag) obj;
        if (this.hashtagId != other.hashtagId) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Hashtag{" + "hashtagId=" + hashtagId + ", name=" + name + '}';
    }

}
