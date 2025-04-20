package com.example.backend.effectmodels;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.SneakyThrows;
import lombok.val;
import lombok.var;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataEffectSkill {

    public static DataEffectSkill previous;
    public SmallImage[] imgs;
    public List<FrameEff> listFrame;
    public int[] sequence;
    public int[][] frameChar = new int[4][];
    public byte b1;
    public int b2;
    public short b3;

    @SneakyThrows
    public void readEffectData(DataInputStream dis) {

        try {
            b1 = dis.readByte();
            b2 = dis.readUnsignedByte();
            b3 = dis.readShort();
            this.imgs = new SmallImage[dis.readByte()];
            for (int i = 0; i < imgs.length; i++) {
                imgs[i] = new SmallImage(dis.readUnsignedByte(),
                        dis.readUnsignedByte(),
                        dis.readUnsignedByte(),
                        dis.readUnsignedByte(),
                        dis.readUnsignedByte());
            }
            var num2 = 0;
            var frame = dis.readShort();
            System.out.println("Frames: " + frame);
            listFrame = new ArrayList<>();
            for (int i = 0; i < frame; i++) {
                val nPart = dis.readByte();
                List<PartFrame> topFrames = new ArrayList<>();
                List<PartFrame> bottomFrames = new ArrayList<>();

                for (int j = 0; j < nPart; j++) {
                    val partFrame = new PartFrame(dis.readShort(), dis.readShort(), dis.readByte());
                    partFrame.flip = dis.readByte();
                    partFrame.onTop = dis.readByte();
                    if (partFrame.onTop == 0) {
                        topFrames.add(partFrame);
                    } else {
                        bottomFrames.add(partFrame);
                    }

                    if (num2 < Math.abs(partFrame.dy)) {
                        num2 = Math.abs(partFrame.dy);
                    }
                }
                listFrame.add(new FrameEff(topFrames, bottomFrames));
            }
            short num4 = (short) dis.readUnsignedByte();
            sequence = new int[num4];
            for (int i = 0; i < num4; i++) {
                sequence[i] = (byte) dis.readShort();
            }
            dis.readByte();
            num4 = dis.readByte();
            frameChar[0] = new int[num4];
            for (int i = 0; i < num4; i++) {
                frameChar[0][i] = dis.readByte();
            }
            num4 = dis.readByte();
            frameChar[1] = new int[num4];
            for (int i = 0; i < num4; i++) {
                frameChar[1][i] = dis.readByte();
            }
            num4 = dis.readByte();
            frameChar[3] = new int[num4];
            for (int i = 0; i < num4; i++) {
                frameChar[3][i] = dis.readByte();
            }
        } finally {
            dis.close();
        }
    }

    @JsonIgnore
    public InputStream getInputStream(Integer id) throws IOException {
        var bos = new ByteArrayOutputStream();
        var dos = new DataOutputStream(bos);

        try {
            dos.writeByte(2);
            dos.writeByte(id);
            dos.writeShort(213);
            dos.writeByte(imgs.length);
            for (int i = 0; i < imgs.length; i++) {
                SmallImage img = imgs[i];
                dos.writeByte((byte) i);
                dos.writeByte((short) img.x);
                dos.writeByte((short) img.y);
                dos.writeByte((short) img.w);
                dos.writeByte((short) img.h);
            }
            var size = listFrame.size();
            dos.writeShort(size);
            for (int i = 0; i < listFrame.size(); i++) {
                FrameEff frame = listFrame.get(i);
                dos.writeByte(frame.listPartTop.size() + frame.listPartBottom.size());
                for (int j = 0; j < frame.listPartTop.size(); j++) {
                    PartFrame partFrame = frame.listPartTop.get(j);
                    dos.writeShort(partFrame.dx);
                    dos.writeShort(partFrame.dy);
                    dos.writeByte(partFrame.idSmallImg);
                    dos.writeByte(partFrame.flip);
                    dos.writeByte(0);
                }


                for (int j = 0; j < frame.listPartBottom.size(); j++) {
                    PartFrame partFrame = frame.listPartBottom.get(j);
                    dos.writeShort(partFrame.dx);
                    dos.writeShort(partFrame.dy);
                    dos.writeByte(partFrame.idSmallImg);
                    dos.writeByte(partFrame.flip);
                    dos.writeByte(1);
                }
            }
            dos.writeByte((byte) sequence.length);
            for (int i = 0; i < sequence.length; i++) {
                dos.writeShort(sequence[i]);
            }
            dos.writeByte(0);
            dos.writeByte(frameChar[0].length);
            for (int i = 0; i < frameChar[0].length; i++) {
                dos.writeByte(frameChar[0][i]);
            }
            dos.writeByte(frameChar[1].length);
            for (int i = 0; i < frameChar[1].length; i++) {
                dos.writeByte(frameChar[1][i]);
            }
            dos.writeByte(frameChar[3].length);
            for (int i = 0; i < frameChar[3].length; i++) {
                dos.writeByte(frameChar[3][i]);
            }
            var result = bos.toByteArray();
            byte[] sizeBuff = new byte[2];
            val num = result.length - 4;
            sizeBuff[0] = (byte) (num >> 8);
            sizeBuff[1] = (byte) (num);
            result[2] = sizeBuff[0];
            result[3] = sizeBuff[1];
            return new ByteArrayInputStream(result);
        } finally {
            dos.close();
            bos.close();
        }

    }

    @Override
    public String toString() {
        return "DataEffectSkill{" +
                "imgs=" + Arrays.toString(imgs) +
                ", listFrame=" + listFrame +
                ", sequence=" + Arrays.toString(sequence) +
                ", frameChar=" + Arrays.toString(frameChar) +
                '}';
    }
}
