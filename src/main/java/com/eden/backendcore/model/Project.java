package com.eden.backendcore.model;

import com.eden.data.model.BaseModel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Where(clause = "is_deleted=false")
@SQLDelete(sql = "update project set is_deleted=true,updated_at=CURRENT_TIMESTAMP where id=?")
public class Project extends BaseModel {

    private String name;
    private String role;
    @Column(name = "description", length = 500)
    private String description;
    @Column(name = "from_date")
    @ColumnDefault("CURRENT_TIMESTAMP")
    private LocalDateTime fromDate;
    @Column(name = "to_date")
    @ColumnDefault("CURRENT_TIMESTAMP")
    private LocalDateTime toDate;
}
