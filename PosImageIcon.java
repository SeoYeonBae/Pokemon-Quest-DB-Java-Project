import javax.swing.*;
import java.awt.*;

// ImageIcon�� ��ǥ�� ��ġ�� �ο��ϰ��� ImageIcon Ŭ������ �����
public class PosImageIcon extends ImageIcon {
	int pX;				// ImageIcon�� X��ǥ
	int pY;				// ImageIcon�� y��ǥ
	int originPX;		// ó�� ������ ��ǥ
	int originPY;
	int width;			// ImageIcon�� �ʺ�
	int height;			// ImageIcon�� ����
	
	public PosImageIcon(String img, int x, int y, boolean u) {
		super(img);
		pX = x;
		pY = y;
		originPX = x;
		originPY = y;
		width = this.getIconWidth();
		height = this.getIconHeight();	
	}
	
	public void setImageIcon(String img) {
		new ImageIcon(img);
	}
	
	public void move(int x, int y) {
		pX += x;
		pY += y;
	}
	
	public void draw(Graphics g) {
		g.drawImage(this.getImage(), pX, pY, width, height, null);
	}
	
	public Point center(PosImageIcon img) {
		return new Point(img.pX+img.width/2, img.pY+img.height/2);
	}
	
	public double distance (Point p1, Point p2) {
		return Math.sqrt(Math.pow((p1.x-p2.x), 2)+ Math.pow((p1.y-p2.y), 2));
	}
	
	public boolean collide (PosImageIcon img, int d) {
		return distance(center(this), center(img)) < d ;
	}
}