import java.util.*;
import java.io.*;

public class Main_bj_2467_용액 {

    public static void main(String[] args) throws Exception {

        System.setIn(new FileInputStream("res/input_2467.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int array[] = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            array[i] = Integer.parseInt(st.nextToken());
        }

        int min = Integer.MAX_VALUE;
        int start = 0;
        int end = N - 1;
        int A = 0;
        int B = 0;

        while (start < end) {
            int mix = array[start] + array[end];

            if(min > Math.abs(mix)){
                min = Math.abs(mix);
                A = array[start];
                B = array[end];
            }
            if (mix >= 0) {
                end--;
            } else {
                start++;
            }
        }
        System.out.println(A + " " + B);
    }
}

