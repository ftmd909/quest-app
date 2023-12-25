package com.project.fd.request;
import com.project.fd.entities.User;
import lombok.Data;

@Data
public class PostCreateRequest {
    Long id;
    String text;
    String title;
    Long userId;
}
