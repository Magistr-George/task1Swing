package test1;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;




public class MyContainer extends JPanel implements MouseListener {

	public int panelsCount=16;
	int highlightIndex=6;
	JLabel[] panels=new JLabel[panelsCount];	
	SwingTemplate st;
	public MyContainer(int windowWidth, int windowHeight, SwingTemplate st) {
		this.setSize(windowWidth, windowHeight);
		this.st=st;
		
		this.setLayout(new GridLayout(4,4));
		

		Font labelFont = this.getFont();
		Font myFont=new Font(labelFont.getName(), Font.PLAIN, 30);



		for (int i=0;i<panelsCount;i++) {
			panels[i] =new JLabel();
			panels[i].setBorder(BorderFactory.createLineBorder(Color.blue));
			panels[i].setFont(myFont);
			panels[i].setText(String.valueOf(i));
			panels[i].setHorizontalAlignment(SwingConstants.CENTER);
			this.add(panels[i]);
			
			if (i==highlightIndex) {
				panels[i].setBorder(BorderFactory.createLineBorder(Color.green, 3));
			}
		}

		for (int i=0;i<panelsCount;i++) {
			panels[i].addMouseListener(this);
		}

		sendInterfaceData();

	}

	private void sendInterfaceData() {
		for (int i=0;i<panelsCount;i++) {
			if (i==highlightIndex) {
				panels[i].setBorder(BorderFactory.createLineBorder(Color.green, 3));
			}else {
				panels[i].setBorder(BorderFactory.createLineBorder(Color.blue));
			}
		}
		st.setTitle("Selected index is " + String.valueOf(highlightIndex));
	}
	


	public void keyLeft() {
		if (highlightIndex>0)
			highlightIndex--;
		
		sendInterfaceData();
	}


	public void keyRight() {
		if (highlightIndex<panelsCount-1)
			highlightIndex++;	
			
		sendInterfaceData();
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {
		for (int i=0;i<panelsCount;i++) {
			if (panels[i]==e.getComponent()) {
				if(panels[i].isVisible()) st.setTitle("Selected index is " + String.valueOf(panels[i].getText()));

					while (i+4<panelsCount) {
						if (panels[i+4].isVisible()) {
							panels[i].setText(String.valueOf(panels[i+4].getText()));
						}else break;

						i+=4;
					}
				panels[i].setVisible(false);
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		for (int i=0;i<panelsCount;i++) {
			if (panels[i]==e.getComponent()) {
				panels[i].setBorder(BorderFactory.createLineBorder(Color.green, 3));
			}else {
				panels[i].setBorder(BorderFactory.createLineBorder(Color.blue));
			}
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {

	}
}
