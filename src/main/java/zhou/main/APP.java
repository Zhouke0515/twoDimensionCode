package zhou.main;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class APP extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFrame jf ;
	public static void main(String[] args) {
		new APP().showFrame();
		
	}
	
	private void showFrame(){
		jf = new JFrame("创建二维码");
		
		JPanel panel = new JPanel();
		
		final JFileChooser jfc = new JFileChooser();
		JButton btn = new JButton("选择LOGO图片");
		
		final JLabel pic = new JLabel();
		jf.setLayout(new BorderLayout());
		jf.add(panel);
		jf.setSize(500, 500);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setVisible(true);
		panel.add(btn);
		btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				jfc.showDialog(new JLabel(), "选择");
				
				
				
				ImageIcon image = new ImageIcon(jfc.getSelectedFile().getAbsolutePath());
				
				pic.setIcon(image);
				
			}
		});
		pic.setSize(300, 300);
		panel.add(pic);
		
		
	}
}
