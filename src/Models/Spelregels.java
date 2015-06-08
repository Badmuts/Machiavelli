package Models;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
/**
 * 
 * 
 * @author Jamie Kalloe
 *
 */

public class Spelregels {
	private String spelregels = "Regel 1: test.";
	
	public String getSpelregels() throws IOException
	{
		return this.getSpelregelsFromResource("spelregels.txt");
	}
	
	public String getSpelregelsFromResource(String fileName)
	{
		String text = null;
		File file = new File("bin" + File.separator + "Resources" + File.separator + "spelregels.txt");
		String absolutePath = file.getAbsolutePath();
		try 
		{
			text = new Scanner( new File(absolutePath), "UTF-8" ).useDelimiter("\\A").next();
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
		
		return text;
	}
	
}