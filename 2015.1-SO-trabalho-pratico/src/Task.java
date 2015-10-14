
public class Task {
	
	//Attributes
	private int id; 
	private int startTime, processingTime, endTime;
	private int length;
	private int priority;
	private TaskState state;
	
	public Task(int id, int startTime, int length, int priority) {
		this.id = id;
		this.startTime = startTime;
		this.processingTime = 0;
		this.length = length;
		this.endTime = Integer.MAX_VALUE;
		this.priority = priority;
		this.state = TaskState.NEW;
		System.out.println(toString());
	}
	
	@Override
	public String toString() {
		return String.format("Task #%d, starts at %ds, has a length of %ds "
				+ "with priority %d", this.id, this.startTime, this.length, this.priority);
	}
}
