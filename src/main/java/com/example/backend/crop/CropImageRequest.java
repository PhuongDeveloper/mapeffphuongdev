package com.example.backend.crop;

import com.example.backend.effectmodels.SmallImage;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CropImageRequest {
    private String image;
    private SmallImage smallImage;
    private int zoomLevel;
}
