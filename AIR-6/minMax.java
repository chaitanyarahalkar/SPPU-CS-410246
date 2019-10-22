
public class minMax {
	
	int return_height(int n)
	{
		if(n==1)
			return 0;
		else
			return 1+return_height(n/2);
	}
	
	public static void main(String[] args)
	{
		int arr[] = {3,5,6,9,1,2,0,-1};
		
		minMax m = new minMax();
		int height = m.return_height(arr.length);
		
		thread1 th1 = new thread1(arr,height);
		
		th1.start();
		
	}

}
