#include <iostream>
#include <algorithm>
#include <omp.h>
#include <vector>
#include <time.h>

using namespace std;

void swap(int &x,int &y){

	int temp = x;
	x = y; 
	y = temp;
}
void bubblesort(int arr[],int size){

	for(int i = 0; i < size - 1; i++){
		#pragma omp for 
		for(int j = 0; j < (size - i - 1); j+=2)
			if(arr[j] > arr[j+1])
				swap(arr[j],arr[j+1]);
	}
}
int main(void){

	srand(0);
	double start,end;
	int size = 10;

	int arr[size];
	for(int i=0; i<size; i++){
		arr[i] = rand();
	}

	start = omp_get_wtime();
	bubblesort(arr,size);
	end = omp_get_wtime();

	cout << "Sorting Time for Parallel Implementation: " << end - start << endl;

	for (int i = 0; i < size; i++)
		cout << arr[i] << endl;
	return 0;
}
