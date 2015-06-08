package Models;

/**
 * 
 * 
 * @author Bernd Oostrum
 *
 */

public class Spelregels {
	
	String spelregels = "D:/test.txt";

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
	
	public String weergeefSpelregels(){
		System.out.println("test");
		return this.spelregels;
	}	
	
	public static void main(String[] args) {
		Spelregels nieuw = new Spelregels();
	}
}