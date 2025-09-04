import java.io.*;
import java.util.*;


public class Main {	
	static int N, M, K, r, c;
	static int [][] board;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		board = new int [N][M];
		
		for(int i=0;i<K;i++) {
			st = new StringTokenizer(br.readLine());
			r = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			
			int [][] sticker = new int[r][c];
			
			for(int a=0;a<r;a++) {
				st = new StringTokenizer(br.readLine());
				for(int b=0;b<c;b++) {
					sticker[a][b] = Integer.parseInt(st.nextToken());
				}
			}
			
			boolean flag = findPlace(sticker, r, c);
			if(!flag) { // 90도
				sticker = rotate90(sticker, r, c);
				flag = findPlace(sticker, c, r);
			}
			if(!flag) { // 180도
				sticker = rotate90(sticker, c, r);
				flag = findPlace(sticker, r, c);
			}
			if(!flag) { // 270도
				sticker = rotate90(sticker, r, c);
				flag = findPlace(sticker, c, r);
			}
		}
		
		System.out.println(countSticker());
		
	}
	
	static int[][] rotate90(int [][] sticker, int n, int m) {
		int [][] rotate = new int [m][n];
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				rotate[j][n-1-i] = sticker[i][j];
			}
		}
		return rotate;
	}
	
	static boolean findPlace(int [][] sticker, int n, int m) {
		int startX=0, startY=0; boolean flag = false;
		for(int i=0;i<N;i++) { // (0,0)부터 시작
			if(i+n>N) break;
			flag = false;
			for(int j=0;j<M;j++) {
				if(j+m>M) break;
				flag = compare(sticker, i, j, n, m);
				if(flag) {
					startX = i; startY=j; break;
				}
			}
			if(flag) break;
		}
		
		if(!flag) return false;
		else {
			pasteBoard(sticker, startX, startY, n, m);
			return true;
		}
	}
	
	static boolean compare(int [][] sticker, int startX, int startY, int n, int m) {
		for(int i=0;i<n;i++) {
			for(int j=0; j<m;j++) {
				if(sticker[i][j]==1&&board[i+startX][j+startY]==1) return false;
			}
		}
		return true;
	}
	
	static void pasteBoard(int [][] sticker, int startX, int startY, int n, int m) {
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				if(board[startX+i][startY+j]==0) {
					board[startX+i][startY+j] = sticker[i][j];
				}
			}
		}
	}	
	
	static int countSticker() {
		int cnt = 0;
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(board[i][j]==1){
					cnt++;
				}
			}
		}
		return cnt;
	}
	
}
