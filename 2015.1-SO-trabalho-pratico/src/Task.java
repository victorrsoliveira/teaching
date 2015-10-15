
public class Task implements Comparable<Task> {
	
	//Attributes
	private int id; 
	private int startTime, remainingTime, endTime;
	private int length;
	private int priority;
	private TaskState state;
	
	public Task(int id, int startTime, int length, int priority) {
		this.id = id;
		this.startTime = startTime;
		this.remainingTime = length;
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
	
	/** Get the length of the task
	 * 
	 * @return The length of the task
	 */
	public int getLength() {
		return this.length;
	}
	
	/** Returns the state of the Task.
	 * 
	 * @return A TaskState that can be {NEW, READY, RUNNING, COMPLETED}.
	 */
	public TaskState getState() {
		return this.state;
	}
	
	/** Sets a new state to the task
	 * 
	 */
	public void setState(TaskState newState) {
		this.state = newState;
	}
	// Returns whether the task has already been completed or not
	public boolean hasCompleted() {
		return this.remainingTime == 0;
	}

	/** Returns the time when task starts
	 * 
	 * @return An integer value for the time of start
	 */
	public int getStartTime() {
		return this.startTime;
	}

	public void decrementTime() {
		this.remainingTime--;
	}
	
	@Override
	public int compareTo(Task t) {
		if (this.startTime == t.startTime)
			return 0;
		else if (this.startTime > t.startTime)
			return 1;
		else 
			return -1;
	}
}
