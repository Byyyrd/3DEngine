package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class Mesh {
    public Triangle[] tris;

    public Mesh() {

    }
    public static Mesh cube(){
        Mesh mesh = new Mesh();
        mesh.tris = new Triangle[]{
                new Triangle(new Vector3[]{new Vector3(0.0, 0.0, 0.0), new Vector3(0.0, 1.0, 0.0), new Vector3(1.0, 1.0, 0.0)}),
                new Triangle(new Vector3[]{new Vector3(0.0, 0.0, 0.0),new Vector3( 1.0, 1.0, 0.0),new Vector3( 1.0, 0.0, 0.0)}),

                new Triangle(new Vector3[]{new Vector3(1.0, 0.0, 0.0),new Vector3( 1.0, 1.0, 0.0),new Vector3( 1.0, 1.0, 1.0)}),
                new Triangle(new Vector3[]{new Vector3(1.0, 0.0, 0.0),new Vector3( 1.0, 1.0, 1.0),new Vector3( 1.0, 0.0, 1.0)}),

                new Triangle(new Vector3[]{new Vector3(1.0, 0.0, 1.0),new Vector3( 1.0, 1.0, 1.0),new Vector3( 0.0, 1.0, 1.0)}),
                new Triangle(new Vector3[]{new Vector3(1.0, 0.0, 1.0),new Vector3( 0.0, 1.0, 1.0),new Vector3( 0.0, 0.0, 1.0)}),

                new Triangle(new Vector3[]{new Vector3(0.0, 0.0, 1.0),new Vector3( 0.0, 1.0, 1.0),new Vector3( 0.0, 1.0, 0.0)}),
                new Triangle(new Vector3[]{new Vector3(0.0, 0.0, 1.0),new Vector3( 0.0, 1.0, 0.0),new Vector3( 0.0, 0.0, 0.0)}),

                new Triangle(new Vector3[]{new Vector3(0.0, 1.0, 0.0),new Vector3( 0.0, 1.0, 1.0),new Vector3( 1.0, 1.0, 1.0)}),
                new Triangle(new Vector3[]{new Vector3(0.0, 1.0, 0.0),new Vector3( 1.0, 1.0, 1.0),new Vector3( 1.0, 1.0, 0.0)}),

                new Triangle(new Vector3[]{new Vector3(1.0, 0.0, 1.0),new Vector3( 0.0, 0.0, 1.0),new Vector3( 0.0, 0.0, 0.0)}),
                new Triangle(new Vector3[]{new Vector3(1.0, 0.0, 1.0),new Vector3( 0.0, 0.0, 0.0),new Vector3( 1.0, 0.0, 0.0)}),

        };
        return mesh;
    }
    public boolean loadObjectFromFile(String filename) {

            Thread thread = new Thread(()-> {
                try {
                    tris = new Triangle[0];
                    File myObj = new File(filename);
                    Scanner reader = new Scanner(myObj);
                    ArrayList<Vector3> vertices = new ArrayList<>();
                    ArrayList<Triangle> triangles = new ArrayList<>();

                    while (reader.hasNextLine()) {
                        String data = reader.nextLine();
                        String[] splitData = data.split(" ");
                        if (Objects.equals(splitData[0], "v")){
                            Vector3 v = new Vector3();
                            v.x = Double.parseDouble(splitData[1]);
                            v.y = Double.parseDouble(splitData[2]);
                            v.z = Double.parseDouble(splitData[3]);
                            vertices.add(v);
                        }
                        if (Objects.equals(splitData[0], "f")){
                            Triangle t = new Triangle();
                            t.p[0] = vertices.get(Integer.parseInt(splitData[1])-1);
                            t.p[1] = vertices.get(Integer.parseInt(splitData[2])-1);
                            t.p[2] = vertices.get(Integer.parseInt(splitData[3])-1);
                            triangles.add(t);
                        }

                    }
                    tris = triangles.toArray(tris);
                    reader.close();
                } catch (FileNotFoundException e) {
                    System.out.println("An error occurred.");
                    e.printStackTrace();
                }
            });
            thread.start();
            return true;
    }
}
