
import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JTextField;

public class display {
	JFrame jf;
	JTextField[] array;
	int height;
	int[] scores;
	int[] complete_tree;
	
	int frame_width;
	int frame_height;
	
	display(int scores[],int height)
	{
		this.height=height;
		this.scores=scores;
		
		
		jf = new JFrame();
		jf.setVisible(true);
		jf.setLayout(null);
		frame_height=800;
		frame_width=800;
		jf.setBounds(400, 0, frame_width, frame_height);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	void add_elements()
	{
		int total_tree_elements = 2*scores.length-1;
		complete_tree = new int[total_tree_elements];
		array = new JTextField[total_tree_elements];
		int unknown_nodes = total_tree_elements-scores.length;
		
		int i=unknown_nodes;
		int j=0;
		while(i<total_tree_elements)
		{
			complete_tree[i]=scores[j];
			i++;
			j++;
		}
		
		
		i=0;
		while(i<unknown_nodes)
		{
			complete_tree[i]=-9;
			i++;
		}
		
		int box_height = 50;
		int box_width = 60;
		
		int rows_pixel = frame_height/(height+1);
		
		int temp = 0;
		Font f = new Font("Arial", Font.PLAIN, 24);
		while(temp<=height)
		{
			int nodes_at_level = (int) Math.pow(2, (height-temp));
			
			int columns_per_box = frame_width/nodes_at_level;
			
			int y = (height-temp)*rows_pixel+((rows_pixel-box_height)/2);
			
			int x = 0 + (columns_per_box-box_width)/2;
			
			
			 j = nodes_at_level-1;
			int k=1;
			for(;k<=nodes_at_level;j++,k++)
			{
				array[j]=new JTextField();
				array[j].setBounds(x,y,box_width,box_height);
				array[j].setText(String.valueOf(complete_tree[j]));
				array[j].setFont(f);
				if(temp==0)
					array[j].setBackground(new Color(150, 150, 255));
				else
					array[j].setBackground(new Color(127, 127, 127));
				
				x=x+box_width+2*((columns_per_box-box_width)/2);
				jf.add(array[j]);
				jf.repaint();
			}
			
			
			temp+=1;
			
			
			
		}
		
		
		
		
	}
	
	void setText(int nodeNo,int depth,int ans)
	{
		int offset = (int) (Math.pow(2, depth)-1);
		array[nodeNo+offset].setText(String.valueOf(ans));
	}
	
	
	public void setNodeColor(int nodeNo)
	{
		int offset = (int) (Math.pow(2, height)-1);
		
		array[nodeNo+offset].setBackground(new Color(50, 220, 50));
	}
	
	
	public void setRoot(int ans)
	{
		array[0].setText(String.valueOf(ans));
	}
}
