import java.awt.image.*;
import java.awt.*;
import java.io.*;
import javax.imageio.ImageIO;
import java.util.*;
 
class Main {

    public static void main(String [] args) throws IOException {
	
	BitInputStream input= new BitInputStream(
						 new FileInputStream("compress.bmp"));
	File f = new File("compress.bmp");

	ArrayList<Color> c =input.lireImage(f);
	
	new Image (f, c);
    }

} 
