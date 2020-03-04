void dfs(int cur, int parent, vector<vector<int>>& adj, vector<pair<int,int>>& crit,  int& id, vector<bool>& visited, vector<int>& low, vector<int>& ids){
   visited[cur] = true;
    ids[cur] = id;
    low[cur] = id;
    id++;
    for (auto next : adj[cur]){
        if(next == parent) continue;
        if(!visited[next]){
            dfs(next, cur, adj, crit, id, visited, low, ids);
            low[cur] = std::min(low[cur], low[next]);
            if(ids[cur] < low[next]){
                crit.push_back(pair<int,int>(cur,next));
            }
        }
        else{
            low[cur] = std::min(low[cur], ids[next]);
        }
    }
}

vector<pair<int, int> > criticalConnections(int numOfWarehouses,  int numOfRoads,  vector<pair<int, int> > roads)
{
    if(numOfRoads == 0 || numOfWarehouses == 0) return {};
    vector<vector<int>> adj(numOfWarehouses+1, vector<int>());
    for(auto road : roads){
        adj[road.first].push_back(road.second);
        adj[road.second].push_back(road.first);
    }
    int id = 0;
    vector<int> ids(numOfWarehouses+1, 0);
    vector<int> low(numOfWarehouses+1, 0);
    vector<bool> visited(numOfWarehouses+1, false);
    vector<pair<int, int>> crit;
    for(int i =1; i<numOfWarehouses+1; i++){
        if(visited[i]) continue;
        dfs(i, -1, adj, crit, id, visited, low, ids);
    }
    return crit;
}
