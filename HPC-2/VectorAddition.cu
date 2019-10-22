#include <iostream>
#include <time.h>
#include <stdexcept>
#include <vector> 
#include <cstdlib>

#define n 20

__global__ void add(int *x,int *y, int *z){

  int id=blockIdx.x; 
  z[id]=x[id]+y[id];
}

int main(){

    srand(time(0));
    int a[n], b[n], c[n];

    int *d,*e,*f;
    
    for(int i=0;i<n;i++){
        a[i] = rand();
        b[i] = rand();
    }

    cudaMalloc((void **)&d,n*sizeof(int)); 
    cudaMalloc((void **)&e,n*sizeof(int));
    cudaMalloc((void **)&f,n*sizeof(int));

    cudaMemcpy(d,a,n*sizeof(int),cudaMemcpyHostToDevice);    
    cudaMemcpy(e,b,n*sizeof(int),cudaMemcpyHostToDevice); 
 
    cudaEvent_t start,stop;
    float elapsed;

    cudaEventCreate(&start);
    cudaEventCreate(&stop);

    cudaEventRecord(start,0);

    add<<<n,1>>>(d,e,f); 

    cudaDeviceSynchronize();
    cudaMemcpy(c,f,n*sizeof(int),cudaMemcpyDeviceToHost); 
    cudaEventRecord(stop,0);

    cudaEventSynchronize(stop);
    cudaEventElapsedTime(&elapsed,start,stop);

    cudaEventDestroy(start);
    cudaEventDestroy(stop);

    std::cout << "Elapsed Time: " << elapsed  << "ms" << std::endl;
    
    cudaFree(d); 
    cudaFree(e);
    cudaFree(f);

    return 0;
}

