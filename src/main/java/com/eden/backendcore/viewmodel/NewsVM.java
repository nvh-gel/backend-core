package com.eden.backendcore.viewmodel;

import com.eden.common.viewmodel.BaseVM;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class NewsVM extends BaseVM {

    private String title;
    private String description;
    private String url;
    private String image;
}
