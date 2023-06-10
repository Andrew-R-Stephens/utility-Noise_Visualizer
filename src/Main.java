import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main {
	
	static CFrame frame = new CFrame();
	
	public static void main(String[] args) {
		
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader("permutations.txt"));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		
		World world = new World();
		try {
			String s = "";
			while((s = br.readLine()) != null) {
				String[] split = s.split(" ");
				if(split.length >= 0)
					world.expandH();
				for(String number: split)
					world.expandW(new Chunk(Double.parseDouble(number)));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		frame.setScale(326,6);
		frame.finalizeFrame();
		frame.importWorld(world);
		frame.repaint();
	}

}

class CFrame extends JFrame{

	CPanel p = new CPanel();
	int s = 1;
	double scale = 1;
	
	World world;

	public CFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void setScale(int worldSize, int mult) {
		scale = mult;
		setPreferredSize(new Dimension(worldSize*mult, worldSize*mult));
	}
	
	public void finalizeFrame() {
		add(p);
		pack();
		setVisible(true);
		setLocationRelativeTo(null);
	}
	
	public void importWorld(World w) {
		this.world = w;				
	}

	class CPanel extends JPanel {
		public void paintComponent(Graphics g) {
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, getWidth(), getHeight());
			for(int y = 0; y < world.getWorld().size(); y++) {
				for(int x = 0; x < world.getWorld().get(y).size(); x++) {
					double v = Math.abs(world.getWorld().get(y).get(x).getData()*255);
					g.setColor(new Color(0, (int)v, 0));
					g.fillRect((int)(x*s*scale), (int)(y*s*scale), (int)((s*scale)), (int)((s*scale)));
				}
			}
		}
	}
	
}
