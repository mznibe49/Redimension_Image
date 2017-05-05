import java.awt.image.*;
import java.awt.*;
import java.io.*;
import javax.imageio.ImageIO;
import java.util.*;


public class BitInputStream extends FilterInputStream {
    int bits;
    int mask;

    public BitInputStream(InputStream in) {
	super(in);
    }

    /**
     * Reads the next bit from this input stream. The value is returned as an
     * int in the range 0 to 1. If no bit is available because the end of the
     * stream has been reached, the value -1 is returned. This method blocks
     * until input data is available, the end of the stream is detected, or an
     * exception is thrown.
     */
    public int readBit() throws IOException {
	int bits;
	int mask = this.mask;

	if (mask == 0) {
	    bits = read();
	    if (bits == -1)
		return -1;

	    this.bits = bits;
	    mask = 0x80;
	} else {
	    bits = this.bits;
	}

	if ((bits & mask) == 0) {
	    this.mask = mask >> 1;
	    return 0;
	} else {
	    this.mask = mask >> 1;
	    return 1;
	}
    }
	
  
    //calcule la couleur moyenne entre 4 couleurs et retourne cette derniere
    public Color medium (Color c1, Color c2, Color c3, Color c4){
	int red = (c1.getRed()+c2.getRed()+c3.getRed()+c4.getRed())/4;
	int green = (c1.getGreen()+c2.getGreen()+c3.getGreen()+c4.getGreen())/4;
	int blue = (c1.getBlue()+c2.getBlue()+c3.getBlue()+c4.getBlue())/4;
	Color medium = new Color (red, green, blue);
	return medium;
	
    }
    
    public ArrayList<Color> lireImage(File f)throws IOException{
	BufferedImage image = ImageIO.read(f);
	int width = image.getWidth();
	int height = image.getHeight();
	System.out.println(width+"    "+height);
	Color c1, c2 , c3 , c4 ;
	ArrayList<Color> list = new ArrayList<Color>();
	for (int i=0; i<width-1; i=i+2){
	    for (int j =0; j<height-1; j=j+2){
		c1 =new Color( image.getRGB(i, j));
		c2 =new Color( image.getRGB(i, j+1));
		c3 =new Color( image.getRGB(i+1, j));
		c4 =new Color( image.getRGB(i+1, j+1));
		//System.out.println(c1+" "+c2+" "+c3+" "+c4);
		Color medium = medium (c1, c2, c3, c4);
		//System.out.println("On a à la position" +i+" / "+j+" **** "+medium);
		list.add(medium);
		
	    }
	}
	return list;
    }




}
