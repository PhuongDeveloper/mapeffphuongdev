package com.example.backend.response;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UploadMapResponse {
    public int width;
    public int height;
    public short[][] maps;
}
