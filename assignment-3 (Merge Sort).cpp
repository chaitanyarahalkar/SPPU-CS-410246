#include <iostream>
#include <omp.h>
#include <time.h>
#include <stdio.h>
#include <string.h>
#include <stdlib.h>

using namespace std;
void merge(int a[],int size, int temp[]){

	int i = 0;
	int j = size / 2;

	int k = 0;

	while(i < size/2 && j < size ){
		if(a[i] <= a[j]){
			temp[k++] = a[i];
			i++;
		}
		else{
			temp[k++] = a[j];
			j++;
		}
	}

	while(i < size/2){
		temp[k++] = a[i];
		i++;
	}
	while(j < size){
		temp[k++] = a[j];
		j++;
	}

	memcpy(a, temp, size * sizeof(int));
}
void mergesort_parallel(int a[],int size,int temp[],int threads){

	if(threads > 1){

		#pragma omp parallel sections
		{
			#pragma omp section
			mergesort_parallel(a, size/2,temp,threads/2);
			#pragma omp section
			mergesort_parallel(a + size/2, size-size/2,temp + size/2, threads-threads/2);
		}
		#pragma omp taskwait
		merge(a,size,temp);
	}
}

int main(void){

	srand(0);
	double start,end;
	int size = 1024;
	int threads = 8;
	int no_of_cores;

    #pragma omp parallel
    {
        #pragma omp master
        {
            no_of_cores = omp_get_max_threads();
        }
    }

    cout << "Number of cores: " << no_of_cores << endl;

    int* arr;
    arr = (int*)malloc(size * sizeof(int)); 
    int temp[size];

    for(int i = 0; i < size; i++) {
    	arr[i] = rand();
    }

    start = omp_get_wtime();
    mergesort_parallel(arr,size,temp,threads);
    end = omp_get_wtime();

    cout << "Sorting Time for Parallel Implementation: " << end - start << endl;
}