package Framework.HelperClasses;

import java.util.*;

public class AIReversi {
    private int[][] p = new int[8][8];
    private HashMap<Integer, Integer> hmap = new HashMap<>();
    private BoardReversi r;
    private int[][] board;

    public AIReversi(BoardReversi r, int[][] board) {
        this.r = r;
        this.board = board;
        determinePriority();
    }

    public void determinePriority() {
        r.legalMoves(1);
        for (int x = 0; x < p[0].length; x++) {
            for (int y = 0; y < p.length; y++) {
                p[x][y] = 200;
                addPriority(x, x, y);
                addPriority(y, x, y);
                if (!(board[x][y] == 3)) {
                    p[x][y] = 0;
                } else {
//                    System.out.println(p[x][y] + "|" + (y*8+x));
                    hmap.put(
                            (y * 8 + x),
                            p[x][y]);
                }
            }
        }
//        printHashMap();
        r.clearLegalMoves();
    }

    public void addToHmap() {

    }

    public void printHashMap() {
        SortHashMap.sortByValue(hmap);
        for (Integer name : hmap.keySet()) {
            System.out.println(name);
        }
        hmap.clear();
    }

    public void addPriority(int i, int x, int y) {
        switch (i) {
            case 0:
                p[x][y] += 50;
            case 1:
                p[x][y] -= 55;
            case 2:
                p[x][y] += 20;
            case 3:
                p[x][y] += 15;
            case 4:
                p[x][y] += 15;
            case 5:
                p[x][y] += 20;
            case 6:
                p[x][y] -= 55;
            case 7:
                p[x][y] += 50;
        }
    }


    static class SortHashMap {
        public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
            List<Map.Entry<K, V>> list = new ArrayList<>(map.entrySet());
            list.sort(Map.Entry.comparingByValue());

            Map<K, V> result = new LinkedHashMap<>();
            for (Map.Entry<K, V> entry : list) {
                result.put(entry.getKey(), entry.getValue());
            }
            return result;
        }
    }
}

