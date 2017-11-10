#include <iostream>
#include <vector>

using namespace std;

struct pic {
  int L, R, B, T;
} pics[15];

bool comp(pic a, pic b) {
  
}

int main() {
  // n: number of pics
  // s: starting height of first ball
  // w: width of room
  int n, s, w;
  cin >> n >> s >> w;
  
  for (int i = 0; i < n; i++)
    cin >> pics[i].L >> pics[i].R >> pics[i].B >> pics[i].T;

  return 0;
}
