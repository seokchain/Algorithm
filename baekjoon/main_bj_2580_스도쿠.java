import java.io.*;
import java.util.*;

public class Main_bj_2580_스도쿠 {

    static int map[][];
    static int flag = 0;

    public static void main(String[] args) throws Exception {

        System.setIn(new FileInputStream("res/input_2580.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        map = new int[9][9];

        for (int i = 0; i < 9; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < 9; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        sdoku(0, 0);

    }

    static void sdoku(int r, int c) {
        if(flag == 1) return;

        if (c == 9) {
            sdoku(r + 1, 0);
            return;
        }

        if (r == 9) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    sb.append(map[i][j]).append(" ");
                }
                sb.append("\n");
            }
            System.out.println(sb);
            flag = 1;
            return;
        }

        if (map[r][c] == 0) {
            for (int n = 1; n <= 9; n++) {
                if (check(r, c, n)) {
                    map[r][c] = n;
                    sdoku(r, c + 1);
                }
            }
            map[r][c] = 0;
            return;
        }
        sdoku(r, c + 1);
    }

    static boolean check(int r, int c, int num) {

        // 가로
        for (int i = 0; i < 9; i++) {
            if (map[r][i] == num) return false;
        }

        // 세로
        for (int i = 0; i < 9; i++) {
            if (map[i][c] == num) return false;
        }

        // 3x3
        int rr = (r / 3) * 3;
        int cc = (c / 3) * 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (map[rr + i][cc + j] == num) return false;
            }
        }
        return true;
    }
}