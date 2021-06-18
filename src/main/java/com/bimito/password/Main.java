package com.bimito.password;

public class Main {

    public static void main(String[] args) {
        int k = 3;
        int password = 123;
        int[] circle1 = {2, 4, 1, 3, 5, 6, 7, 8, 9};
        int[] circle2 = {9, 8, 7, 5, 4, 6, 2, 3, 1};
        int[] circle3 = {9, 5, 6, 8, 7, 4, 2, 3, 1};

        int answer = new Main().findAnswer(k, password, circle1, circle2, circle3);
        System.out.println("This is answer: " + answer);
    }


    public int findAnswer(int k, int password, int[]... circles) {
        if (k != getLengthOfPassword(password) || k != circles.length)
            throw new RuntimeException("Invalid inputs! count of circles and length of password must be equal with k");

        int answer = 0;
        String passString = String.valueOf(password);
        int i = 0;
        for (int[] circle : circles) {
            int pointer = 0;
            int n = Integer.parseInt(passString.substring(i, i + 1));
            i++;
            int indexOfN = indexOfN(n, circle);

            if (indexOfN - pointer < circle.length - indexOfN)
                answer += indexOfN - pointer;
            else
                answer += circle.length - indexOfN;
        }

        return answer;
    }

    private int getLengthOfPassword(int password) {
        return String.valueOf(password).length();
    }

    private int indexOfN(int n, int[] circle) {
        for (int i = 0; i < circle.length; i++) {
            if (n == circle[i])
                return i;
        }

        throw new RuntimeException("It didn't find n");
    }
}
