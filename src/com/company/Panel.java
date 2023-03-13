package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Panel extends JPanel implements ActionListener {
    int screenWidth = 500;
    int screenHeight = 500;
    double theta;
    double dt = 30;
    Matrix matProj;
    Mesh meshCube = new Mesh();
    Vector3 Camera = Vector3.Zero();
    Matrix matRotZ = new Matrix();
    Matrix matRotX = new Matrix();

    public Panel() {
        this.setBounds(0, 0, screenWidth, screenHeight);
        this.setBackground(Color.BLACK);
        this.setVisible(true);
        matProj = Matrix.projectionMatrix(0.1, 1000.0, 90.0, screenWidth / screenHeight);


        meshCube.loadObjectFromFile("src/Spaceship.obj");
        //meshCube = Mesh.cube();
        Timer timer = new Timer((int) dt, this);
        timer.start();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;


        //Matrix matRotated = Matrix.Rotate(theta,0,theta);
        Matrix matRotatedX = Matrix.RotateX(theta);
        Matrix matRotatedZ = Matrix.RotateZ(theta * 0.5);
        Matrix matTranslated = Matrix.Translate(0, 0, 10);
        Matrix matScale = Matrix.Scale(1, 1, 1);

        Matrix matWorld;
        matWorld = Matrix.Identity();
        matWorld = Matrix.MultiplyMatrices(matRotX, matRotZ);
        matWorld = Matrix.MultiplyMatrices(matWorld, matTranslated);

        ArrayList<Triangle> triangles = new ArrayList<>();

        for (Triangle tri : meshCube.tris) {
            Triangle triProjected = new Triangle(), triTransformed = new Triangle();

            triTransformed.p[0] = Vector3.MultiplyMatrixVector(tri.p[0], matWorld);
            triTransformed.p[1] = Vector3.MultiplyMatrixVector(tri.p[1], matWorld);
            triTransformed.p[2] = Vector3.MultiplyMatrixVector(tri.p[2], matWorld);

            //Calc Normals and normalize
            Vector3 normal;
            normal = Vector3.getNormal(triTransformed);
            normal = Vector3.normalize(normal);

            // Get Ray from triangle to camera
            Vector3 vCameraRay = Vector3.sub(triTransformed.p[0], Camera);

            if (Vector3.dotProduct(vCameraRay, normal) < 0) {

                //Light
                Vector3 lightDirection = new Vector3(0, 0, -1);
                lightDirection = Vector3.normalize(lightDirection);

                double dp = Vector3.dotProduct(lightDirection, normal);

                triTransformed.setColor(dp);

                // Project triangles from 3D --> 2D
                triProjected.p[0] = Vector3.MultiplyMatrixVector(triTransformed.p[0], matProj);
                triProjected.p[1] = Vector3.MultiplyMatrixVector(triTransformed.p[1], matProj);
                triProjected.p[2] = Vector3.MultiplyMatrixVector(triTransformed.p[2], matProj);
                triProjected.color = triTransformed.color;

                triProjected.p[0] = Vector3.div(triProjected.p[0], triProjected.p[0].w);
                triProjected.p[1] = Vector3.div(triProjected.p[1], triProjected.p[1].w);
                triProjected.p[2] = Vector3.div(triProjected.p[2], triProjected.p[2].w);


                // Scale into view
                triProjected.p[0].x += 1.0f;
                triProjected.p[0].y += 1.0f;
                triProjected.p[1].x += 1.0f;
                triProjected.p[1].y += 1.0f;
                triProjected.p[2].x += 1.0f;
                triProjected.p[2].y += 1.0f;
                triProjected.p[0].x *= 0.5f * getWidth();
                triProjected.p[0].y *= 0.5f * getHeight();
                triProjected.p[1].x *= 0.5f * getWidth();
                triProjected.p[1].y *= 0.5f * getHeight();
                triProjected.p[2].x *= 0.5f * getWidth();
                triProjected.p[2].y *= 0.5f * getHeight();


                triangles.add(triProjected);
                // Rasterize triangle

            }
        }
        triangles.sort((t1, t2) -> {
            double z1 = (t1.p[0].z + t1.p[1].z + t1.p[2].z) / 3.0f;
            double z2 = (t2.p[0].z + t2.p[1].z + t2.p[2].z) / 3.0f;
            return Double.compare(z2, z1);

        });
        for (Triangle triProjected : triangles) {


            g2d.setColor(triProjected.color);
            g2d.fillPolygon(new int[]{(int) triProjected.p[0].x, (int) triProjected.p[1].x, (int) triProjected.p[2].x},
                            new int[]{(int) triProjected.p[0].y, (int) triProjected.p[1].y, (int) triProjected.p[2].y},
                            3);
/*                g2d.setColor(Color.BLACK);
                g2d.drawPolygon(new int[]{(int) triProjected.p[0].x, (int) triProjected.p[1].x, (int) triProjected.p[2].x},
                        new int[]{(int) triProjected.p[0].y, (int) triProjected.p[1].y, (int) triProjected.p[2].y},
                        3);*/
        }
        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        theta += (dt / 60) * 0.05;


        matRotZ = Matrix.RotateZ(theta);
        matRotX = Matrix.RotateX(theta * 0.5);


    }
}
