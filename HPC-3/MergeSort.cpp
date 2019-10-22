#include <omp.h>   
#include <iostream> 
#include <stdlib.h>
#include <time.h>

using std::cout;
using std::endl;

void merge(int* arr, int start, int mid, int end){

  int len = (end - start) + 1;
  int temp[len];
  int k = 0;
  
  int i = start;
  int j = mid + 1;
  while(i <= mid && j <= end){

    if(arr[i] <= arr[j])
      temp[k++] = arr[i++];

    else
      temp[k++] = arr[j++];
  }

  if(i <= mid)
    while(i <= mid)
      temp[k++] = arr[i++];

  else if(j <= end)
    while(j <= end)
      temp[k++] = arr[j++];


  k = 0;
  for(i = start; i <= end; i++){

    arr[i] = temp[k];
    k++;
  }

}

void merge_sort(int* arr, int start, int end){
    
        
    if(start < end){
      int mid = (start + end) / 2;

      #pragma omp parallel sections
      {
        #pragma omp section
        merge_sort(arr, start, mid);

        #pragma omp section
        merge_sort(arr, mid + 1, end);
      }

       merge(arr, start, mid, end); 

    }
}


int main(void){ 
  
  int size = 10;
  int arr[size];
  double start,end;

  for(int i = 0; i< size; i++){

    arr[i] = rand();
  }
   
  start = omp_get_wtime(); 
  merge_sort(arr, 0, size - 1);
  end = omp_get_wtime();

  cout << "Sorting Time for Parallel Implementation: " << end - start << "s" << endl;

  for (int i = 0; i < size; i++)
    cout << arr[i] << " ";

  cout << endl;
  return 0;

}