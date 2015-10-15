import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;


public class FCFSScheduler {

	private int currentTime, totalTime;
	private ArrayList<Task> tasks;
	private Task currentTask;
	private LinkedList<Task> waitingList = new LinkedList<>();
	private BufferedWriter writer;
	
	public FCFSScheduler(ArrayList<Task> tasks, BufferedWriter writer) {
		this.currentTime= 0;
		this.tasks = tasks;
		// Initialize totalTime variable, which is the time to execute all
		// of the tasks
		calculateTotalTime();
		this.currentTask = null;
		this.writer = writer;
	}
	
	private void calculateTotalTime() {
		int totalTime = 0;
		for (Task t : this.tasks) {
			totalTime += t.getLength();
		}
		this.totalTime = totalTime;
	}
	
	//Implement function to be called in the main process (Run)
	//Make sure appropriate return type
	public void schedule() {
		for (; this.currentTime < this.totalTime; this.currentTime++) {
			// 3 simple steps
			// First, look at the task running in the processor and FREE the processor
			// Second, if task t starts in the curtentTime, put it in the waitingList
			// Third, if the processor is FREE, move a task from the waitingList to it
			// Part 1
			if (this.currentTask != null) {
				if (this.currentTask.hasCompleted()) {
					this.currentTask.setState(TaskState.COMPLETED);
					this.currentTask = null;
				} else {
					// If quantum was to be considered
				}
			}
			
			//Part 2 - Look for tasks to be processed (READY)
			for (Task t : tasks) {
				// If test below is true that means task t is READY to be processed
				if (t.getStartTime() == this.currentTime) {
					t.setState(TaskState.READY);
					this.waitingList.add(t);
				}
			}
			
			//Part 3 - Look for a task to be moved into the processor
			if (this.currentTask == null) {
				// In this scheduler(FCFS), the first task in the waitingList will be served 
				if (this.waitingList != null) {
					Task t = this.waitingList.removeFirst();
					t.setState(TaskState.RUNNING);
					this.currentTask = t;
				}
			}
			
			String lineOutput = String.format("%2d-%2d   ", this.currentTime, this.currentTime + 1);
			for (Task t : tasks) {
				if (t.getState() == TaskState.RUNNING) {
					lineOutput += "##";
					t.decrementTime();
				}
				else if (t.getState() == TaskState.READY)
					lineOutput += "--";
				else
					lineOutput += "  ";
				if (tasks.iterator().hasNext())
					lineOutput += "  ";
			}
			try {
				this.writer.write(lineOutput + '\n');
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}
}
