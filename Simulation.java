import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Simulation {
	
	public static void main(String[] args) {
		simulate(3600, 10);
	}// main()

	public static void simulate( int seconds, int ppm) {
		Printer p= new Printer(ppm);
		Queue<Task> printerQueue= new LinkedList <Task>();
		int totalWaitTime=0; 
		int nTasks=0;
		ArrayList <Integer> waitTimes = new ArrayList<Integer> ();
		for(int time= 1; time <= seconds; time ++) {
			if(newPrintTask()==true) {
				Task t= new Task(time);
				printerQueue.add(t);
				nTasks++;
				System.out.println ("\n" + t);
				
				
			}
			if(!p.busy() && !printerQueue.isEmpty()) {
				Task t= printerQueue.remove();
				
				//record wait time
				int currentTime = t.getPages() * 60 /ppm +time;
				System.out.println("Time to print the task: " +t.getPages() * 60 /ppm);
				int waitTime =  t.waitTime(currentTime);
				System.out.println("The wait time is: " + waitTime + " seconds");
				waitTimes.add(waitTime);
				
				
				p.startNext(t);
	
			}
			
			p.tick();
		}
		
		for(int i=0; i< waitTimes.size(); i++) {
			totalWaitTime = totalWaitTime + waitTimes.get(i);
		}
		System.out.println("\nNumber of tasks: " +nTasks);
		System.out.println("Average wait time: "+ totalWaitTime/ (float) nTasks+ " seconds = " 
		+ (totalWaitTime/ (float) nTasks)/60 + " minutes");	
	}//simulate()
	
	public static boolean newPrintTask() { //I DON'T UNDERSTAND THIS PART
		return (180 == ((int) (1+ 180 * Math.random())));
	}
	
}//Class Simulation
