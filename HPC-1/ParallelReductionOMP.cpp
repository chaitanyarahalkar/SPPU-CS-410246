
/*
Notes

Compilation: 

OMP_NUM_THREADS=5 g++ -fopenmp -o binary assignment1.cpp 

Built in reductions: 

- +,∗,−,max,min (Arithmetic)					  
- &,&&,|,||,^  (Logical)

User defined reductions: 

#pragma omp declare reduction \
( identifier : typelist : combiner ) \
[initializer(initializer-expression)]


*/


#include <iostream>
#include <omp.h>
#include <time.h>
#include <vector>

using namespace std;


int main(void){

	srand(0);
	int SIZE =  1 << 12;
	int arr[SIZE];
	
	for (int i = 0; i < SIZE; i++)
		arr[i] = rand();

	double start, end;

	start = omp_get_wtime(); // Elapsed wall clock time in seconds


	// Maximum value 
	int max_val = 0;

	#pragma omp parallel for reduction(max:max_val) // Special request to the compiler is made by pragma
	for(int j = 0; j < SIZE; j++)
		if(arr[j] > max_val)
			max_val = arr[j];

	cout << "Maximum Element:" <<  max_val << endl;


	// Minimum value 

	int min_value = INT_MAX;

	#pragma omp parallel for reduction(min:min_value)
	for (int j = 0; j < SIZE; j++)
		if(arr[j] < min_value)
			min_value = arr[j];

	cout << "Minimum Element:" << min_value << endl;


	// Sum 
	int sum = 0;

	#pragma omp parallel for reduction(+:sum)
	for (int j = 0; j < SIZE; j++)
		sum += arr[j];

	cout << "Sum of Elements:" << sum << endl;

	// Average

	cout << "Average of Elements:" << sum / SIZE << endl;

	end = omp_get_wtime();

	cout << "Time taken: " << end - start << "s" << endl;

	return 0;
}