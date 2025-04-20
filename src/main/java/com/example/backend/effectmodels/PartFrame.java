package com.example.backend.effectmodels;

public class PartFrame {
    public PartFrame(int dx, int dy, int idSmall) {
        this.idSmallImg = (short) idSmall;
        this.dx = (short) dx;
        this.dy = (short) dy;
    }

    public short idSmallImg;
    public short dx;
    public short dy;
    public byte flip;
    public byte onTop;

    @Override
    public String toString() {
        return "PartFrame{" +
                "idSmallImg=" + idSmallImg +
                ", dx=" + dx +
                ", dy=" + dy +
                ", flip=" + flip +
                ", onTop=" + onTop +
                '}';
    }
}
