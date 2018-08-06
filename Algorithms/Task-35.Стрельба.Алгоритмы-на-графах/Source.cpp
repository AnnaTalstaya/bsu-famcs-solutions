#include <fstream>
#include <iostream>
#include <vector>
#include <queue>
#include <list>


std::ifstream input_file("input.txt");
std::ofstream output_file("output.txt");

int r, c;
int count = 0, n, m, w;

std::vector<std::vector<int>> arr_I(m);
std::vector<std::vector<int>> adj_list;
std::vector<std::vector<int>> connectivity_components;
std::vector<int> vec;
std::vector<bool> cont_cycle;
std::list<int> list;
bool cycle_is_found = false;
bool* visit;
std::vector<int> met;

std::vector<std::vector<int>> bipartite_graph;
std::vector<int> current_match;
std::vector<char> used;

void dfs_find_comp(int v) {
	visit[v] = true;
	vec.push_back(v);

	for (int i = 0; i < adj_list[v].size(); i++) {
		w = adj_list[v][i];
		if (!visit[w])
			dfs_find_comp(w);
	}
}

void find_components() {
	for (int i = 1; i < m; i++)
		visit[i] = false;

	for (int i = 1; i < m; i++)
		if (!visit[i]) {
			vec.clear();
			dfs_find_comp(i);
			connectivity_components.push_back(vec);
		}
}

void dfs_find_cycle(int v) {
	visit[v] = true;
	count++;

	for (int i = 0; i < adj_list[v].size(); i++) {
		w = adj_list[v][i];
		if (!visit[w])
			dfs_find_cycle(w);
		else if (count >= 3)
			cycle_is_found = true;
		else count++;
		if (cycle_is_found)
			break;
	}
}

void find_cycle() {
	cont_cycle.resize(connectivity_components.size());
	for (int i = 1; i < m; i++)
		visit[i] = false;

	for (int i = 0; i < connectivity_components.size(); i++) {
		std::vector<int> vector = connectivity_components[i];
		cycle_is_found = false;

		if (!visit[vector[0]]) {
			count = 0;
			dfs_find_cycle(vector[0]);
			if (!cycle_is_found) {
				output_file << "No";
				break;
			}
		}
	}
}

void make_bipartite_graph() {
	for (int i = 1; i < m; i++) {
		for (int j = 1; j < n; j++) {
			if (arr_I[i][j] == 1) {
				bipartite_graph[i].push_back(j);
			}
		}
	}
}

bool try_kuhn(int v) {
	if (used[v])  return false;
	used[v] = true;
	for (size_t i = 0; i<bipartite_graph[v].size(); ++i) {
		int to = bipartite_graph[v][i];
		if (current_match[to] == -1 || try_kuhn(current_match[to])) {
			current_match[to] = v;
			return true;
		}
	}
	return false;
}


int main() {
	input_file >> r >> c;
	m = r + 1;
	n = c + 1;

	adj_list.resize(m);
	arr_I.resize(m);
	visit = new bool[n];

	//initialize incidence matrix
	for (int i = 1; i < m; i++)
		for (int j = 0; j < n; j++)
			arr_I[i].push_back(0);

	for (int j = 1, i; j < n; j++)
		for (int k = 0; k < 2; k++) {
			input_file >> i;
			arr_I[i][j] = 1;
		}

	//build adjacency list	
	std::queue<int> q;
	int a, b;
	for (int i = 1; i < n; i++) {
		for (int j = 1; j < m; j++)
			if (arr_I[j][i] == 1) {
				q.push(j);
				if (q.size() == 2)
					break;
			}
		a = q.front();
		q.pop();
		b = q.front();
		q.pop();
		
		adj_list[a].push_back(b);
		adj_list[b].push_back(a);
	}

	//find connectivity components
	find_components();
	find_cycle();
	if (cycle_is_found) {
		bipartite_graph.resize(m);
		make_bipartite_graph();
		current_match.assign(n, -1);

		for (int v = 1; v<m; ++v) {
			used.assign(m, false);
			try_kuhn(v);
		}
		for (int v = 1; v<n; ++v) {
			int val = current_match[v];
			if (val == -1) 
				for (int i = 1; i < m; i++)
					if (arr_I[i][v] == 1) {
						val = i;
						break;
					}
			
			output_file << val << " ";
		}
	
	}
	output_file.close();
	return 0;
}