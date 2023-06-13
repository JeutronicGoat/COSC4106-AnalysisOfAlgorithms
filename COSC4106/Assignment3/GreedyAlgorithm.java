import java.util.Arrays;
import java.util.Collections;

//Jacob Culp
//Creates a schedule by adding jobs as late as possible without breaking their deadline while
//also adding the highest profit jobs first and ignoring any job that cannot fit into the schedule.
public class GreedyAlgorithm {
    public static void main(String[] args){
    	
//    	Job[] jobs = {
//                new Job("j1", 100, 2),
//                new Job("j2", 10, 1),
//                new Job("j3", 15, 2),
//                new Job("j4", 27, 1)
//        };
    	
        Job[] jobs = {
                new Job("j1", 5, 3),
                new Job("j2", 15, 2),
                new Job("j3", 10, 1),
                new Job("j4", 20, 2),
                new Job("j5", 1, 3)
        };

        //Sorting highest profit first (because we are greedy)
        //Using Arrays and Collections allows me to easily sort my list of object in descending order
        Arrays.sort(jobs, Collections.reverseOrder());	
        Schedule schedule = new Schedule(jobs.length);

        for(int i = 0; i < jobs.length; i++){
            schedule.addJob(jobs[i]);
        }
        schedule.printSchedule();
    }
	
//************************************************************	
	public static class Job implements Comparable{
        String jobName;
        int profit;
        int deadline;

        Job(String name, int profit, int deadline){
            this.jobName = name;
            this.profit = profit;
            this.deadline = deadline;
        }

        @Override
        public int compareTo(Object o) {
            Job j2 = (Job) o;
            if(profit > j2.profit) return 1;
            else if(profit < j2.profit) return -1;
            else return 0;
        }
    }
//************************************************************	
	//Assumes you are feeding it presorted list of jobs in descending order
	private static class Schedule{
        Job[] timeSlots;
        int numOfJobs;
        int totalProfit;

        Schedule(int n){
            timeSlots = new Job[n];
            numOfJobs = 0;
            totalProfit = 0;
        }

        //Add to the first available time slot to the right that is still within the deadline
        //Since we are giving it a presorted list of jobs in descending order we are adding 
        //the highest profit jobs as late as possible.
        void addJob(Job job){
            for(int i = job.deadline - 1; i >= 0; i--){
                if(timeSlots[i] == null){
                    timeSlots[i] = job;
                    numOfJobs++;
                    totalProfit += job.profit;
                    break;
                }
            }
        }

        void printSchedule(){
            for(int i = 0, j = 0; i < timeSlots.length-1; i++){
                if(timeSlots[i] == null) continue;
                //Only shows '-->' if it isn't the last entry
                j++;
                if(j < numOfJobs)	System.out.print(timeSlots[i].jobName + " --> ");
                else 				System.out.print(timeSlots[i].jobName);
            }
            System.out.println("\nThe total profit is "+totalProfit+".");
        }
    }
//************************************************************
}
