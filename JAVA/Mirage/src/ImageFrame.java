package src;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

public class ImageFrame extends javax.swing.JFrame {
	private Canvas canvas;

	private JButton smaller;

	private JButton bigger;

	private float scale = 1f;

	/**
	 * Auto-generated main method to display this JFrame
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				
				ImageFrame inst = new ImageFrame("iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAMAAAFfKj/FAAAACXBIWXMAAAsTAAALEwEAmpwYAAAABGdBTUEAALGOfPtRkwAAACBjSFJNAAB6JQAAgIMAAPn/AACA6QAAdTAAAOpgAAA6mAAAF2+SX8VGAAADAFBMVEUAgMsAi9sAgMpV6f8AWJgRbKP8/v8ec6VV6v8Ks/6F/v+G/v0Do/IAgsYnyfMAmOq0//iR//3T//kSu//H//f8/f4KZ4kNe5stzfwAjNuu4vae1/W47P207/xUhKE93P9b7f80mMmK/P62//larNkAY59T5Pw7f6gbbqCj//xvip2j//qd//td6f84ksR5z+j19vew//tso8DH//lW2vofyP8gxfsmcJ0yyPPD//d3m7C3//fA5PUyeKKT/v4AofOBxt4AkOFl9f+T6v6L0uxb+P8yiLsFrPkAgs4RbKTO//i51+T9/v8Ags8Am+0Quv8Quf/K//kAcbV7+f4AiNiN/v0dx/8Pre8Ab7JertIYbJ4Da5MZZZIEm+W83++arLhg8v+K+fc2eJ8VuPbt+f9n8v86ndALpeE80vkAj+AhfbJG4f8uzPT2+vzA8fshxPJHmcJ3+f/E//c42P8ewfn5/P1O7fs+xPQDmd9QvebV//fp7fAAWZUKsvwpzP6s//vM//in//oAfsgKqO8sbZZRhKOq5PfT7vqX/PwAfcRY6/8AZ6z///+NjY2Ojo6Pj4+QkJCRkZGSkpKTk5OUlJSVlZWWlpaXl5eYmJiZmZmampqbm5ucnJydnZ2enp6fn5+goKChoaGioqKjo6OkpKSlpaWmpqanp6eoqKipqamqqqqrq6usrKytra2urq6vr6+wsLCxsbGysrKzs7O0tLS1tbW2tra3t7e4uLi5ubm6urq7u7u8vLy9vb2+vr6/v7/AwMDBwcHCwsLDw8PExMTFxcXGxsbHx8fIyMjJycnKysrLy8vMzMzNzc3Ozs7Pz8/Q0NDR0dHS0tLT09PU1NTV1dXW1tbX19fY2NjZ2dna2trb29vc3Nzd3d3e3t7f39/g4ODh4eHi4uLj4+Pk5OTl5eXm5ubn5+fo6Ojp6enq6urr6+vs7Ozt7e3u7u7v7+/w8PDx8fHy8vLz8/P09PT19fX29vb39/f4+Pj5+fn6+vr7+/v8/Pz9/f3+/v7///8Ey57QAAAAjXRSTlP//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////wDYXlVrAAABU0lEQVR42mLuUQIIICaG0wABxNDTAxBADFJSAAHE2MPwiQ8ggFgYSjhYAAKIoUdKqgcggJgY7D4zAAQQEwOHGANAADExvPdgAAggoCzDaeU1aQwAAcTCwMBweD+QBAggJgaGv97Kio8ZAAIIyJjzwuDYYwaAAAKpeb03goEBIICAIrdWy75lYAAIIKAyrsSPD4QZAAIIKCJzY7ExAwNAAAFFbquovhJjAAggFoY+G6nJ3wSKAAKIhYH3xyceRUEGgABiYhA2eagobM8AEEAgcxgY+uK2soQ/WVcEZAIEEMhOBoaAp9fUtt3iBDEBAogJLLDhf2XSMfmfICZAAEFUaG0ykefl0wQxAQIIJNAnwpE8n4NRd+bXR0UMAAEEEpA4x/nS+abk4/qbbxgYAAIIZEYQk8CHLyovNLbOe8zAABBAIBUcsbeZH951uav8O4iBASDAAMrSSU8m8uPWAAAAAElFTkSuQmCC");		
				
//				ImageFrame inst = new ImageFrame("E:\\Index\\kq.png");
//				ImageFrame inst = new ImageFrame("http://mail.neusoft.com/imx/center_strip_consumer_1.gif");
//				ImageFrame inst = new ImageFrame("http://www.baidu.com/img/baidu_sylogo1.gif");
//				ImageFrame inst = new ImageFrame();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
	}

	public ImageFrame() {
		super();
		initGUI("");
	}

	public ImageFrame(String file) {
		super();
		initGUI(file);
	}

	Image image;

	private void initGUI(String file) {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			getContentPane().setLayout(null);
			{
				
				if(file.contains("http://")){
					URL url = new URL(file);
					ImageIcon icon = new ImageIcon(url);
					image = icon.getImage();
					System.out.println("网络");
				}else if(file.contains(":\\")){
					Toolkit tk = getToolkit();
					image = tk.getImage(file);
					System.out.println("本地");
				}else{
			    	byte[] picByte= Base642File.decode(file.toCharArray());
					image = ImageIO.read(new ByteArrayInputStream(picByte));
					System.out.println("图片流");
				}
				
				
				
				// 这里的地址填上你图片的位置
			}
			
			
			
			
			{
				canvas = new Canvas() {
					public void paint(Graphics g) {
						super.paint(g);
						Graphics2D g2d = (Graphics2D) g;
						AffineTransform at = new AffineTransform();
						at.scale(scale, scale);
						g2d.setTransform(at);
						g2d.drawImage(image, at, ImageFrame.this);
					}

				};
				getContentPane().add(canvas);
				canvas.setBounds(0, 0, 795, 514);
			}
			{
				bigger = new JButton();
				getContentPane().add(bigger);
				bigger.setText("放 大");
				bigger.setBounds(79, 526, 109, 28);
				bigger.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (scale < 5)
							scale += 0.1;
						canvas.repaint();
					}
				});
			}
			{
				smaller = new JButton();
				getContentPane().add(smaller);
				smaller.setText("缩 小");
				smaller.setBounds(310, 526, 109, 28);
				smaller.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (scale > 0.5)
							scale -= 0.1;
						canvas.repaint();
					}
				});
			}
			pack();
			setSize(800, 600);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}