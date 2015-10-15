import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Run {

	private String inputFileName = "";
	private String outputFileName = "output_files/output";
	private String schedAlgorithm = "";
	
	private ArrayList<Task> tasks = new ArrayList<>();
	
	private static int taskID = 0;
	
	public static void main(String[] args) {
		Run r = new Run();

		//Get parameters
		r.inputFileName = args[0];
		r.schedAlgorithm = args[1];

		//Read input file
		try {
			FileReader inputFile = new FileReader(r.inputFileName);
			BufferedReader bufferReader = new BufferedReader(inputFile);

			String line;

			while((line = bufferReader.readLine()) != null)   {
				//Split line by whitespace (" ")
				// Each line will be formated like this:
				// [startTime	length	priority]
				String[] str = line.split(" ");
				int startTime = Integer.parseInt(str[0]);
				int length = Integer.parseInt(str[1]);
				int priority = Integer.parseInt(str[2]);
				// Create a task and add it to the array tasks
				r.tasks.add(new Task(taskID++, startTime, length, priority));
			}
			//Close the buffer reader
			bufferReader.close();
		} catch(Exception e) {
			System.out.println("Error while reading file line by line:" + e.getMessage());                      
		}
		
		// Sort tasks according to starting time
		Collections.sort(r.tasks);
		
		//Create output file and write diagram header
		try {
			FileWriter outputFile = new FileWriter(r.outputFileName);
			BufferedWriter bufferWriter = new BufferedWriter(outputFile);
			String header = "tempo   ";
			for (int i = 1; i <= r.tasks.size(); i++) {
				header += String.format("P%d  ", i);
			}
			bufferWriter.write(header + "\n");
			
			// Call scheduler
			if (r.schedAlgorithm.equals("fcfs")) {
				FCFSScheduler scheduler = new FCFSScheduler(r.tasks, bufferWriter);
				scheduler.schedule();
			}
			bufferWriter.close();
		} catch (IOException e) {
			System.out.println("Error!!!" + e.getMessage());
		}
	}

}
