package com.company;

public class Vector3 {
    double x;
    double y;
    double z;
    double w = 1;

    public Vector3(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector3(Vector3 vector) {
        this.x = vector.x;
        this.y = vector.y;
        this.z = vector.z;
        this.w = vector.w;
    }

    public Vector3() {

    }

    public static Vector3 Zero() {
        return new Vector3(0, 0, 0);
    }

    public static Vector3 MultiplyMatrixVector(Vector3 i, Matrix m) {
        Vector3 o = new Vector3();
        o.x = i.x * m.m[0][0] + i.y * m.m[1][0] + i.z * m.m[2][0] + m.m[3][0];
        o.y = i.x * m.m[0][1] + i.y * m.m[1][1] + i.z * m.m[2][1] + m.m[3][1];
        o.z = i.x * m.m[0][2] + i.y * m.m[1][2] + i.z * m.m[2][2] + m.m[3][2];
        o.w = i.x * m.m[0][3] + i.y * m.m[1][3] + i.z * m.m[2][3] + m.m[3][3];

        return o;
    }
    public static double length(Vector3 v)
    {
        return Math.sqrt(dotProduct(v, v));
    }
    public static double dotProduct(Vector3 v1, Vector3 v2)
    {
        return v1.x*v2.x + v1.y*v2.y + v1.z * v2.z;
    }
    public static Vector3 normalize(Vector3 v)
    {
        double l = length(v);
        return new Vector3(v.x / l, v.y / l, v.z / l);
    }
    public static Vector3 getNormal(Triangle t) {
        //Calc Lines and get Cross Product
        return crossProduct(sub(t.p[1], t.p[0]),sub(t.p[2], t.p[0]));
    }

    public static Vector3 crossProduct(Vector3 v1, Vector3 v2) {
        Vector3 v = new Vector3();
        v.x = v1.y * v2.z - v1.z * v2.y;
        v.y = v1.z * v2.x - v1.x * v2.z;
        v.z = v1.x * v2.y - v1.y * v2.x;
        return v;
    }
    public static Vector3 sub(Vector3 v1, Vector3 v2)
    {
        return new Vector3( v1.x - v2.x, v1.y - v2.y, v1.z - v2.z );
    }
    public static Vector3 mul( Vector3 v1, double k)
    {
        return new Vector3(v1.x * k, v1.y * k, v1.z * k );
    }

    public static Vector3 div( Vector3 v1, double k)
    {
        return new Vector3( v1.x / k, v1.y / k, v1.z / k );
    }
}
