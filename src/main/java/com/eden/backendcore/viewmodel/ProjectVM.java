package com.eden.backendcore.viewmodel;

import com.eden.common.viewmodel.BaseVM;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
public class ProjectVM extends BaseVM {

    private String name;
    private String role;
    private String description;
    private LocalDateTime fromDate;
    private LocalDateTime toDate;
}
