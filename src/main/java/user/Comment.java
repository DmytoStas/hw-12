package user;

import lombok.Data;

@Data
public class Comment {
    private int postId;
    private int id;
    private String name;
    private String email;
    private String body;

    @Override
    public String toString() {
        return "{" +
                "postId='" + postId + '\'' +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", body='" + body +
                "'}";
    }
}
