# cs61b
Following cs61b - spring 2018: https://sp18.datastructur.es/ cs61b-sp18 

repo: https://github.com/Berkeley-CS61B/skeleton-sp18

1.  #include <iostream>
2.  using namespace std;
3.
4.  class OOP244 {
5.     int *ids;
6.     int size;
7.     int counter;
8.   public:
9.     OOP244(const int *ids_array, int n) {
10.       ids = new int[n];
11.       ids = ids_array;
12.       size = n;
13.    }
14.    ~OOP244() {
15.       delete ids;
16.    }
17.    void display() const {
18.       for (counter = 0; counter < size; counter++)
19.          cout << "id: " << ids[size] << endl; 
20.    }
21. };
22. int main() {
23.     int ids[3] = {23145, 22134, 27112};
24.     OOP244 saa, *sbb = new OOP244(ids, 3);    
25.     sbb.display();
26.     saa.display();  
27.     delete sbb;
28.     return 0;
29. }
