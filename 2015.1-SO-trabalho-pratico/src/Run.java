import java.io.BufferedReader;
import java.io.FileReader;

public class Run {

	String fileName = "";
	String schedAlgorithm = "";

	public static void main(String[] args) {
		Run r = new Run();

		//Get parameters
		r.fileName = args[0];
		r.schedAlgorithm = args[1];

		//Read input file
		try {
			//Create object of FileReader
			FileReader inputFile = new FileReader(r.fileName);

			//Instantiate the BufferedReader Class
			BufferedReader bufferReader = new BufferedReader(inputFile);

			//Variable to hold the one line data
			String line;

			// Read file line by line and print on the console
			while ((line = bufferReader.readLine()) != null)   {
				System.out.println(line);
			}
			//Close the buffer reader
			bufferReader.close();
		} catch(Exception e) {
			System.out.println("Error while reading file line by line:" + e.getMessage());                      
		}

	}

}
