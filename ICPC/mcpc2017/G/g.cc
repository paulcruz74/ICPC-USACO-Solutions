#include <iostream>
#include <vector>
#include <unordered_set>

#define MAXN 1005
#define MAXM 10005

#define pb push_back

using namespace std;

unordered_set<int> ans;
vector<int> adj[MAXN];
int forced[MAXN];
bool mark[MAXN][2];

void dfs(int curr, bool bugged) {
  // cout << "DB: passing on " << curr << " while " << (bugged?"":"not") << " bugged" << endl;
  if (mark[curr][bugged]) {
    // cout << "DB: already visited " << curr << " while " << (bugged?"":"not") << " bugged" << endl;
    return;
  } else {
    mark[curr][bugged] = true;
  }

  if (!bugged) {
    for (unsigned int i = 0; i < adj[curr].size(); i++) {
      dfs(adj[curr][i], true);
    }
  } 

  if (forced[curr] != 0) {
    // cout << "Db: " << curr << " is forced to " << forced[curr] << endl;
    dfs(forced[curr], bugged);
  } else {
    // cout << "yay possible " << curr << endl;
    ans.insert(curr);
  }
}


int main() {
  int n, m;
  cin >> n >> m;  

  for (int i = 0; i < m; i++) {
    int a, b; 
    cin >> a >> b;

    if (a < 0) {
      a = a * (-1);
      forced[a] = b;
    }

    adj[a].pb(b);
  }

  dfs(1, false);

  cout << ans.size() << endl;
  return 0;
}
