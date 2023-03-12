package com.company;

import java.awt.*;

public class Triangle {
    Vector3[] p = new Vector3[]{new Vector3(),new Vector3(),new Vector3()};
    Color color = new Color(247, 0, 255);

    public Triangle(Vector3[] p) {
        this.p = p;
    }

    public Triangle() {
    }
    public void setColor(double lightLevel){
        if(lightLevel > 1){
            color = Color.WHITE;
        }else if (lightLevel < 0){
            color = Color.BLACK;
        }else {
            color = new Color((int) (lightLevel * 255), (int) (lightLevel*255), (int) (lightLevel*255));
        }


    }
}
