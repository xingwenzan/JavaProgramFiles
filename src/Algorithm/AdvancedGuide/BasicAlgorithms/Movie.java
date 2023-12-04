package Algorithm.AdvancedGuide.BasicAlgorithms;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.HashMap;

/**
 * 电影
 * 题目
 * <a href="https://www.acwing.com/problem/content/105/">...</a>
 * 排序
 * <a href="https://www.cnblogs.com/xuwc/p/13945050.html">...</a>
 */
public class Movie {
    private final int[][] movies;
    private int ans;

    public Movie(int @NotNull [] p, int[] b, int[] c, int m) {
        movies = new int[m][3];
        HashMap<Integer, Integer> people = new HashMap<>();
        for (int j : p) {
            if (people.containsKey(j)) {
                people.put(j, people.get(j) + 1);
            } else {
                people.put(j, 1);
            }
        }
        for (int i = 0; i < m; i++) {
            movies[i] = new int[]{people.getOrDefault(b[i], 0), people.getOrDefault(c[i], 0), i + 1};
        }
    }

    private void sort() {
        /*
        Arrays.sort(movies, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0]==o2[0]){
                    if (o1[1]==o2[1]){
                        return o1[2]-o2[2];
                    }else {
                        return o1[1]-o2[1];
                    }
                }else {
                    return o1[0]-o2[0];
                }
            }
        });

         */
        Arrays.sort(movies, (o1, o2) -> o1[0] != o2[0] ? o1[0] - o2[0] : o1[1] != o2[1] ? o1[1] - o2[1] : o1[2] - o2[2]);
        ans = movies[movies.length - 1][2];
    }

    public int getAns() {
        sort();
        return ans;
    }
}
