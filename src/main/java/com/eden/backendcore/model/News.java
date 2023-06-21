package com.eden.backendcore.model;

import com.eden.data.model.BaseModel;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class News extends BaseModel {

    private String title;
    private String description;
    private String url;
    private String image;
}
