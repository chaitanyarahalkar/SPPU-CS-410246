
public class thread2 extends Thread{
	
	int[] scores;
	int height;
	display d;
	
	int MIN=-1000;
	int MAX=1000;
	
	public thread2(int[] s,int h) {
		// TODO Auto-generated constructor stub
		this.scores=s;
		this.height=h;
		d=new display(scores, height);
		d.add_elements();
	}
	
	public void run()
	{
		try {
			this.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int result = alphaBeta(0, 0, true, scores, height, MIN, MAX);
		System.out.println("Result is = "+result);
	}
	
	
	int alphaBeta(int depth,int nodeNo,boolean isMax,int[] scores,int height,int alpha,int beta)
	{
		if(depth==height)
		{
			d.setNodeColor(nodeNo);
			try {
				this.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return scores[nodeNo];
		}
		
		else if(isMax)
		{
			int best = MIN;
			for(int i=0;i<2;i++)
			{
				int val = alphaBeta(depth+1, nodeNo*2+i, false, scores, height, alpha, beta);
				best = Math.max(best, val);
				alpha=Math.max(best, alpha);
				
				if(beta<=alpha)
					break;
			}
			
			d.setText(nodeNo, depth, best);
			try {
				this.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return best;
		}
		
		else
		{
			int best = MAX;
			for(int i=0;i<2;i++)
			{
				int val = alphaBeta(depth+1, nodeNo*2+i, true, scores, height, alpha, beta);
				best = Math.min(best, val);
				beta=Math.min(beta, best);
				
				if(beta<=alpha)
					break;
			}
			
			d.setText(nodeNo, depth, best);
			try {
				this.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return best;
		}
	}

}
