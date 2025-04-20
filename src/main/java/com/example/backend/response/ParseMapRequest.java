package com.example.backend.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ParseMapRequest {
    public String fileName;
    public int width;
    public int height;
    public List<List<Short>> maps;
}
