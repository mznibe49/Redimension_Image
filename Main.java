import java.awt.image.*;
import java.awt.*;
import java.io.*;
import javax.imageio.ImageIO;
import java.util.*;
 
class Main {

    public static void main(String [] args) throws IOException {
       
	BitInputStream input= new BitInputStream(
						 new FileInputStream(args[0]));
	File f = new File(args[0]);

	ArrayList<Color> c =input.lireImage(f);
	
	new Image (f, c);
    }

} 
