package com.eden.backendcore.model;

import com.eden.data.model.BaseModel;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Where(clause = "is_deleted=false")
@SQLDelete(sql = "update service set is_deleted=true, updated_at=CURRENT_TIMESTAMP where id=?")
public class Service extends BaseModel {

    private String title;
    private String description;
}
