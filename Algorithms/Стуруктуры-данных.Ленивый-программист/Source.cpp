#include <fstream>
#include <iomanip>
#include <vector>
#include <set>
#include <algorithm>

/*
 были ошибки из-за 
 if (a[2] == b[2])
 return a[0] > b[0];
 и 
 multuset

 на 
 4
 4 34 10
 8 77 2
 8 12 4
 10 67 2
 и 
3
2 3 3
12 3 6
23 4 6
*/

std::ifstream fin("lazy.in");
std::ofstream fout("lazy.out");

double min_sum = 0;
int n;
std::vector <std::vector<double> > data;
std::multiset<std::pair<double, double> > used_days;
double days = 0;

void read_file() {
	fin >> n;
	data.resize(n);
	int a, b, d;
	for (int i = 0; i < n; i++) {
		fin >> a >> b >> d;
		data[i].push_back(a);
		data[i].push_back(b);
		data[i].push_back(d);
	}
}

void count_min_sum() {
	std::sort(data.begin(), data.end(), [](const std::vector<double>& a, const std::vector<double>& b) {
		if (a[2] == b[2])
			return a[0] > b[0];
		else
			return a[2] < b[2];
	});

	double pay_days;
	for (int i = 0; i < data.size(); i++) {
		if ((pay_days = (data[i][2] - days)) < data[i][1]) {
			if (used_days.size() != 0 && (used_days.rbegin()->first > data[i][0])) {
				double del_days = data[i][1] - pay_days;
				while (used_days.size() > 0) {
					if ((used_days.rbegin()->first > data[i][0]) && (used_days.rbegin()->second >= del_days)) {
						days -= del_days;
						min_sum += (del_days / (used_days.rbegin()->first));
						double d = used_days.rbegin()->second - del_days;
						double c = used_days.rbegin()->first;
						used_days.erase(--used_days.end());

						if (d > 0)
							used_days.insert(std::make_pair(c, d));
						days += data[i][1];
						del_days = 0;
					}
					else if (used_days.rbegin()->first > data[i][0]) {
						double temp = used_days.rbegin()->second;
						min_sum += (temp / (used_days.rbegin()->first));
						days -= temp;
						del_days -= temp;
						used_days.erase(--used_days.end());
					}
					else {
						break;
					}

					if (del_days == 0)
						break;
				}

				if (del_days > 0) {
					min_sum += (del_days / data[i][0]);
					pay_days = data[i][1] - del_days;
					days += pay_days;
					data[i][1] = pay_days;
				}
			}
			else {
				if (pay_days == 0) {
					min_sum += ((data[i][1] - 0) / data[i][0]);
					data[i][1] = 0;
				}
				else {
					min_sum += ((data[i][1] - pay_days) / data[i][0]);
					days += pay_days;
					data[i][1] = pay_days;
				}

			}
		}
		else {
			days += data[i][1];
		}

		if (data[i][1] > 0)
			used_days.insert(std::make_pair(data[i][0], data[i][1]));
	}
}

int main() {

	read_file();
	count_min_sum();

	fout << std::fixed;
	fout << std::showpoint;
	fout << std::setprecision(2) << min_sum;

	fout.close();
	return 0;
}