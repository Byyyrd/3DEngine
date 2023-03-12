package com.company;

public class Matrix {
    public double[][] m = new double[4][4];

    public Matrix(double[][] m) {
        this.m = m;
    }

    public Matrix() {

    }

    public static Matrix MultiplyMatrices(Matrix m1, Matrix m2) {
        Matrix matrix = new Matrix();
        for (int c = 0; c < 4; c++)
            for (int r = 0; r < 4; r++)
                matrix.m[r][c] = m1.m[r][0] * m2.m[0][c] + m1.m[r][1] * m2.m[1][c] + m1.m[r][2] * m2.m[2][c] + m1.m[r][3] * m2.m[3][c];
        return matrix;
    }

    public static Matrix projectionMatrix(double near, double far, double fov, double aspectRatio) {
        double fovRad = 1 / Math.tan(fov * 0.5 / 180f * Math.PI);
        Matrix matrix = new Matrix();
        matrix.m[0][0] = aspectRatio * fovRad;
        matrix.m[1][1] = fovRad;
        matrix.m[2][2] = far / (far - near);
        matrix.m[3][2] = (-far * near) / (far - near);
        matrix.m[2][3] = 1.0f;
        matrix.m[3][3] = 0.0f;
        return matrix;
    }

    public static Matrix Identity() {
        return new Matrix(new double[][]{
                {1, 0, 0, 0},
                {0, 1, 0, 0},
                {0, 0, 1, 0},
                {0, 0, 0, 1}
        });
    }
    public static Matrix Translate(double x, double y, double z) {
        return new Matrix(new double[][]{
                {1, 0, 0, x},
                {0, 1, 0, y},
                {0, 0, 1, z},
                {0, 0, 0, 1}
        });
    }

    public static Matrix Scale(double x, double y, double z) {
        return new Matrix(new double[][]{
                {x, 0, 0, 0},
                {0, y, 0, 0},
                {0, 0, z, 0},
                {0, 0, 0, 1}
        });
    }

    public static Matrix RotateX(double x) {
        Matrix matRotX = new Matrix(new double[][]{
                {1, 0, 0, 0},
                {0, Math.cos(x * 0.5f), Math.sin(x * 0.5f), 0},
                {0, -Math.sin(x * 0.5f), Math.cos(x * 0.5f), 0},
                {0, 0, 0, 1}
        });


        return matRotX;
    }
    public static Matrix RotateY(double y) {
        Matrix matRotY = new Matrix(new double[][]{
                {Math.cos(y * 0.5f), 0, 0, -Math.sin(y * 0.5f)},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {Math.sin(y * 0.5f), 0, 0, Math.cos(y * 0.5f)}
        });
        return matRotY;
    }
    public static Matrix RotateZ( double z) {
        Matrix matRotZ = new Matrix(new double[][]{
                {Math.cos(z * 0.5f), Math.sin(z * 0.5f), 0, 0},
                {-Math.sin(z * 0.5f), Math.cos(z * 0.5f), 0, 0},
                {0, 0, 0, 1},
                {0, 0, 0, 1}
        });
        return matRotZ;
    }



}
