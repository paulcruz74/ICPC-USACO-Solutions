#include <iostream>
#include <queue>
#include <algorithm>
#include <utility>

#define MAXN 10005
#define mp make_pair

using namespace std;

struct conf {
  int beg, e1, e2;
  double prob;
} confs[MAXN];

int main() {
  int n;
  cin >> n;

  for (int i = 0; i < n; i++) {
    cin >> confs[i].beg >> confs[i].e1 >> confs[i].e2;
    confs[i].e1 += confs[i].beg;
    confs[i].e2 += confs[i].beg;
  }

  int ans = n-1;
  confs[n-1].prob = 1;
  for (int i = n-1; i >= 0; i--) {
    priority_queue<pair<double, int>> q;
    int denom = confs[i].e2 - confs[i].e1 + 1; 
    //cout << "Starting on " << i << " with deno " << denom << endl;

    for (int j = i+1; j < n; j++)
      q.push(mp(confs[j].prob, confs[j].beg));

    confs[i].prob = 1;
    int lastN = confs[i].e1 - 1;
    while (!q.empty() && lastN <= confs[i].e2) {
      double probV = q.top().first;
      int nV = q.top().second;
      //cout << "dB: doing " << nV << " at prob " << probV << endl;
      nV = min(nV, confs[i].e2);
      q.pop();
    
      if (nV > lastN) {
        int nom = nV - lastN;
        //cout << "got nominator " << nom << endl;
        confs[i].prob += probV * (((double) nom) / denom);
        lastN = nV;
      }
    }
 
    if (confs[i].prob > confs[ans].prob)
      ans = i;    
    //cout << "my prob " << confs[i].prob << endl;
  }

  printf("%.8lf\n", confs[ans].prob);
  return 0;
}
