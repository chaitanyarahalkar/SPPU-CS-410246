
public class thread1 extends Thread {
	
	
	int[] scores;
	int height;
	display d;
	
	public thread1(int[] s,int h) {
		// TODO Auto-generated constructor stub
		this.scores=s;
		this.height=h;
		d=new display(scores, height);
		d.add_elements();
	}
	public void run()
	{
		int result = minMax(0, 0, true, scores, height);
		System.out.println("Result is = "+result);
	}
	
	
	int minMax(int depth,int nodeNo,boolean isMaxPlaying,int[] scores,int height)
	{
		//System.out.println("node no = "+nodeNo);
		if(depth==height) {
			d.setNodeColor(nodeNo);
			try {
				this.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return scores[nodeNo];
		}
		
		else if(isMaxPlaying)
		{
			
			int ans = Math.max(minMax(depth+1, nodeNo*2, false, scores, height), minMax(depth+1, nodeNo*2+1, false, scores, height));
			/*System.out.println("in MAX");
			System.out.println("depth = "+(depth));
			System.out.println("nodes  = "+nodeNo);*/
			d.setText(nodeNo, depth, ans);
			try {
				this.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return ans;
		}
		
		else
		{
			int ans =  Math.min(minMax(depth+1, nodeNo*2, true, scores, height), minMax(depth+1, nodeNo*2+1, true, scores, height));
			/*System.out.println("in MIN");
			System.out.println("depth = "+(depth));
			System.out.println("nodes  = "+nodeNo);*/
			d.setText(nodeNo, depth, ans);
			try {
				this.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return ans;
		}
		
			
	}

}
