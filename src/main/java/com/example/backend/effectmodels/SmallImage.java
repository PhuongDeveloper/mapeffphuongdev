package com.example.backend.effectmodels;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SmallImage {
    public short id;
    public short x;
    public short y;
    public short w;
    public short h;

    public SmallImage(int id, int x, int y, int w, int h) {
        this.id = (short) id;
        this.x = (short) x;
        this.y = (short) y;
        this.w = (short) w;
        this.h = (short) h;

    }

    public SmallImage() {

    }


    @Override
    public String toString() {
        return "SmallImage{" +
                "id=" + id +
                ", x=" + x +
                ", y=" + y +
                ", w=" + w +
                ", h=" + h +
                '}';
    }
}