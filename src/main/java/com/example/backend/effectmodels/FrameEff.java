package com.example.backend.effectmodels;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

public class FrameEff {

    public FrameEff(List<PartFrame> listtop, List<PartFrame> listbottom) {
        this.listPartTop = listtop;
        this.listPartBottom = listbottom;
    }

    @JsonIgnore
    public List<PartFrame> getListPartPaint() {
        List<PartFrame> myVector = new ArrayList<>();
        myVector.addAll(this.listPartBottom);
        myVector.addAll(this.listPartTop);
        return myVector;
    }

    public List<PartFrame> listPartTop;
    public List<PartFrame> listPartBottom;

    @JsonIgnore
    public byte xShadow;
    @JsonIgnore
    public byte yShadow;

    @Override
    public String toString() {
        return "FrameEff{" +
                "listPartTop=" + listPartTop +
                ", listPartBottom=" + listPartBottom +
                '}' + '\n';
    }
}