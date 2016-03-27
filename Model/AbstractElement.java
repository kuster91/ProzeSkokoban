package Model;

import java.awt.Image;

public class AbstractElement {
    private int x;
    private int y;
    private Image image;

    public AbstractElement(int x, int y) {
    	this.x = x;
    	this.y = y;
    }



	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public Image getImage() {
		return image;
	}
	public void setImage(Image image) {
		this.image = image;
	}

}
