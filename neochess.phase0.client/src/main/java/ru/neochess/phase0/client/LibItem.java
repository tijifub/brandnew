package ru.neochess.phase0.client;

import java.util.Map;
import java.util.function.BiFunction;

/**
 * Created by for on 01.11.16.
 */
public class LibItem {
    private String code;
    private String desc;
    private String race;
    private String moveGenerator;

    private String imgPath;
    private PlacementInterface placementFunc;
    private int size;

    public LibItem(String code, String desc, String imgPath,String race, String moveGenerator, PlacementInterface p) {
        this.code = code;
        this.desc = desc;
        this.race = race;
        this.moveGenerator = moveGenerator;

        this.imgPath = imgPath;
        this.placementFunc = p;
        this.size = 1;
    }

    public LibItem(String code, String desc, String imgPath,String race, String moveGenerator, int size, PlacementInterface p) {
        this.code = code;
        this.desc = desc;
        this.race = race;
        this.moveGenerator = moveGenerator;

        this.imgPath = imgPath;
        this.placementFunc = p;
        this.size = size;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public String getImgPath() {
        return imgPath;
    }

    public String getRace() {
        return race;
    }

    public int getSize() {
        return size;
    }

    public PlacementInterface getPlacementFunc() {
        return placementFunc;
    }
public String getMoveGenerator()
    {return moveGenerator;}

}
