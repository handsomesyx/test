package com.syx.horse;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class HorseChessBoard {
    private static int x;   //列
    private static int y;   //行
    private static boolean[] visited;
    private static boolean finished;


    public static void main(String[] args) {
        System.out.println("start");
        x = 6;
        y = 6;
        visited = new boolean[x * y];
        int[][] chess = new int[y][x];
        traversalHorseChess(chess, 5, 1 , 1);

        for (int[] arr : chess) {
            System.out.println(Arrays.toString(arr));
        }
    }

    public static void traversalHorseChess(int[][] chess, int row, int cloumn, int step) {
        chess[row][cloumn] = step;
        visited[row * y + cloumn] = true;

        List<Point> list = next(new Point(cloumn, row));
        sort(list);
        while (!list.isEmpty()) {
            Point p = list.remove(0);
            if (!visited[p.y * y + p.x]) {
                traversalHorseChess(chess, p.y, p.x, step + 1);
            }
        }

        if (step < (x * y) && !finished) {
            chess[row][cloumn] = 0;
            visited[row * y + cloumn] = false;
        } else {
            finished = true;
        }



    }

    public static void sort(List<Point> list) {
        Collections.sort(list, (p1, p2) -> {
            return next(p1).size() - next(p2).size();
        });
    }

    public static List<Point> next(Point curPoint) {
        List<Point> result = new ArrayList<>();

        Point p = new Point();
        if ((p.x = curPoint.x - 2) >= 0 && (p.y = curPoint.y - 1) >= 0) {
            result.add(new Point(p));
        }
        if ((p.x = curPoint.x - 1) >= 0 && (p.y = curPoint.y - 2) >= 0) {
            result.add(new Point(p));
        }
        if ((p.x = curPoint.x + 1) < x && (p.y = curPoint.y - 2) >= 0) {
            result.add(new Point(p));
        }
        if ((p.x = curPoint.x + 2) < x && (p.y = curPoint.y - 1) >= 0) {
            result.add(new Point(p));
        }
        if ((p.x = curPoint.x + 2) < x && (p.y = curPoint.y + 1) < y) {
            result.add(new Point(p));
        }
        if ((p.x = curPoint.x + 1) < x && (p.y = curPoint.y + 2) < y) {
            result.add(new Point(p));
        }
        if ((p.x = curPoint.x - 1) >= 0 && (p.y = curPoint.y + 2) < y) {
            result.add(new Point(p));
        }
        if ((p.x = curPoint.x - 2) >= 0 && (p.y = curPoint.y + 1) < y) {
            result.add(new Point(p));
        }

        return result;
    }
}
