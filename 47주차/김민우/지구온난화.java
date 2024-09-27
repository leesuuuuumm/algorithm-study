import java.io.*;
import java.util.*;

public class 지구온난화 {

  static int r, c;
  static char[][] graph;
  static char[][] copyGraph;
  static int[] dx = {0, 1, 0, -1};
  static int[] dy = {1, 0, -1, 0};

  public static void main(String[] args) throws IOException{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    r = Integer.parseInt(st.nextToken());
    c = Integer.parseInt(st.nextToken());
    graph = new char[r][c];
    copyGraph = new char[r][c];

    for (int i = 0; i < r; i++) {
      String str = br.readLine();
      for (int j = 0; j < c; j++) {
        graph[i][j] = str.charAt(j);
        copyGraph[i][j] = str.charAt(j);
      }
    }

    // logic
    // 1. 50년 후 땅의 상태 재현하기 (지도 축소 제외)
    for (int i = 0; i < r; i++) {
      for (int j = 0; j < c; j++) {
        if (copyGraph[i][j] == 'X') {
          int cnt = 0; // 상하좌우에 물이 3개 이상인지 확인
          for (int d = 0; d < 4; d++) {
            int nx = i + dx[d];
            int ny = j + dy[d];

            if (nx >= 0 && ny >= 0 && nx < r && ny < c) { // 유효한 범위라면 내부에서 바다인지 탐색 후 cnt
              if (copyGraph[nx][ny] == '.') {
                cnt++;
              }
            } else { // 유효한 범위가 아니라면 모두 바다임.
              cnt++;
            }
          }

          if (cnt >= 3) {
            graph[i][j] = '.';
          }

        }
      }
    }

    // copy 그래프 갱신
    for (int i = 0; i < r; i++) {
      for (int j = 0; j < c; j++) {
        copyGraph[i][j] = graph[i][j];
      }
    }

    // 2. 지도 축소하기

    // 윗편
    for (int i = 0; i < r; i++) {
      int cnt = 0; // 행이 모두 바다인지 판별
      for (int j = 0; j < c; j++) {
        if (copyGraph[i][j] == '.') {
          // 땅의 수를 cnt해 열의 개수와 일치하면 해당 행은 모두, 바다이기에 삭제처리(' ')로 바꿔준다.
          cnt++;
        }
      }

      if (cnt == c) {
        for (int j = 0; j < c; j++) {
          graph[i][j] = ' ';
        }
      } else {
        break;
      }
    }

    // 아랫편
    for (int i = r - 1; i >= 0; i--) {
      int cnt = 0;
      for (int j = 0; j < c; j++) {
        if (copyGraph[i][j] == '.') {
          cnt++;
        }
      }

      if (cnt == c) {
        for (int j = 0; j < c; j++) {
          graph[i][j] = ' ';
        }
      } else {
        break;
      }
    }


    // 열 처리
    // 좌
    for (int i = 0; i < c; i++) {
      int cnt = 0;
      for (int j = 0; j < r; j++) {
        if (copyGraph[j][i] == '.') {
          cnt++;
        }
      }

      if (cnt == r) {
        for (int j = 0; j < r; j++) {
          graph[j][i] = ' ';
        }
      } else {
        break;
      }
    }

    // 우
    for (int i = c - 1; i >= 0; i--) {
      int cnt = 0;
      for (int j = r - 1; j >= 0; j--) {
        if (copyGraph[j][i] == '.') {
          cnt++;
        }
      }

      if (cnt == r) {
        for (int j = 0; j < r; j++) {
          graph[j][i] = ' ';
        }
      } else {
        break;
      }
    }

    // 결과 출력
    for (int i = 0; i < r; i++) {
      int cnt = 0;
      for (int j = 0; j < c; j++) {
        if (graph[i][j] != ' ') {
          System.out.print(graph[i][j]);
          cnt++;
        }
      }
      if (cnt > 0) {
        System.out.println();
      }
    }


  }
}
