import java.io.*;
import java.util.*;

public class Main_bj_21608_상어초등학교 {

    static int N, score, classroom[][], std[][];
    static Map map[][];
    static int di[] = {-1, +1, 0, 0};
    static int dj[] = {0, 0, -1, +1};

    public static void main(String[] args) throws Exception {

        //1. 학생의 번호와 좋아하는 숫자 저장
        //2. N^2 배열 생성 후 자리 배치 시작
        //3. 좋아하는 학생이 가장 많이 포함되는 자리에 배치
        //4. 3을 만족하는 자리가 많으면  빈자리가 많은 칸에 배치
        //5. 4를 만족하는 자리가 많으면 행의 번호가 작은자리로
        //6. 5를 만족하는 자리가 많으면 열의 번호가 작은자리로
        //7. 학생 만족도 구하기 (1명 : 1점, 2명 : 10점, 3명 : 100점, 4명 : 1000점)

        System.setIn(new FileInputStream("res/input_21608.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        classroom = new int[N][N]; //교실을 나타내는 배열 생성
        std = new int[N * N][5]; // 학생의 번호와 좋아하는 학생을 담을 배열 생성

        for (int i = 0; i < N * N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < 5; j++) {
                std[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i<N*N; i++){
            map = new Map[N][N];
            for (int j = 0; j<N; j++){
                for(int k = 0; k<N; k++){
                    map[j][k] = new Map(0, 0);
                }
            }
            sitStd(i);
        }
        sumScore();
        System.out.println(score);
    }

    static void sitStd(int no) {
        int maxLike = -1;
        int maxZero = -1;
        int sitX = 0;
        int sitY = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (classroom[i][j] == 0) search(i, j, no);
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (classroom[i][j] == 0) {
                    if (map[i][j].like > maxLike) {
                        maxLike = map[i][j].like;
                        maxZero = map[i][j].zero;
                        sitY = i;
                        sitX = j;
                    } else if (map[i][j].like == maxLike) {
                        if (map[i][j].zero > maxZero) {
                            maxZero = map[i][j].zero;
                            sitY = i;
                            sitX = j;
                        }
                    }
                }
            }
        }
        classroom[sitY][sitX] = std[no][0];
    }

    static void search(int i, int j, int no) {
        int zero = 0;
        int like = 0;

        for (int d = 0; d < 4; d++) {
            int ni = i + di[d];
            int nj = j + dj[d];

            if (ni >= 0 && ni < N && nj >= 0 && nj < N) {
                if (classroom[ni][nj] == 0) zero++;
                for (int n = 1; n < 5; n++) {
                    if (classroom[ni][nj] == std[no][n]) like++;
                }
            }
        }
        map[i][j].like = like;
        map[i][j].zero = zero;
    }

    static void sumScore(){
        for(int i=0; i < N; i++){
            for(int j=0; j<N; j++){
                for(int no=0; no<N*N; no++){
                    if(std[no][0] == classroom[i][j])
                        search(i,j, no);
                }
                score += Math.pow(10, map[i][j].like-1);
            }
        }
    }

    static class Map {
        int like;
        int zero;

        Map(int like, int zero) {
            this.like = like;
            this.zero = zero;
        }
    }
}
