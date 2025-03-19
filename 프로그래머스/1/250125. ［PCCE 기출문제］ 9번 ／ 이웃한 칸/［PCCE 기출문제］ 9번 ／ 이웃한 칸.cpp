#include <string>
#include <vector>
#include <queue>
using namespace std;

queue<pair<int,int>>q;

int dx[4] = {0,1,-1,0};
int dy[4] = {1,0,0,-1};
int X, Y;

int bfs(vector<vector<string>> &board, int h, int w){
    int res = 0;
    string str = board[h][w];
    
   for(int i=0;i<4;i++){    
       int nextX = h+dx[i];
            int nextY = w+dy[i];
            if(nextX<0||nextY<0||nextX>=X||nextY>=Y) continue;
            if(board[nextX][nextY]==str){
                res++;
            }
    }
    return res;
}

int solution(vector<vector<string>> board, int h, int w) {
    int answer = 0;   
    X = board.size();
    Y = board[0].size();
 
    q.push({h, w});
    answer = bfs(board, h, w);
    
    return answer;
}