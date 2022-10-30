package com.test.danstest.utils;

public class Test1 {
    public static void main(String[] args) {
        int rows = 8;
        for (int i = 1; i <= rows; i++)
        {
            double center = Math.pow(2,i-1);
            for (int j = rows; j > i; j--)
            {
                System.out.print("  ");
            }
            for (int j = 0; Math.pow(2,j) <= center; j++)
            {
                System.out.printf("%.0f ",Math.pow(2,j));
            }
            for (double value = center/2; value >= 1; value=value/2)
            {
                System.out.printf("%.0f ",value);
            }
            System.out.println();
        }
    }
}
