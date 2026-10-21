import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class ScoreListAnalyzer
{
	public static void main(String[] args) {
		List<Integer> scores = new ArrayList<>();
		
		scores.add(88);
		scores.add(72);
		scores.add(95);
		scores.add(81);
		scores.add(72);
		System.out.println("Original Scores: " + scores);
		int sum = 0;
		for (int score : scores) {
		    sum += score;
		}
		double average = (double) sum / scores.size();
		System.out.println("Average score: " + average);
		
		Collections.sort(scores);
		System.out.println("Sorted scores: " + scores);
		
		int search81 = Collections.binarySearch(scores, 81);
		if(search81 >= 0) 
		{
		    System.out.println("81 Found at index " + search81);
		}
		else
		{
		    System.out.println("81 not found");
		}
		
		int search100 = Collections.binarySearch(scores, 100);
		if(search100 >= 0)
		{
		    System.out.println("100 Found at index " + search100);
		}
		else
		{
		    System.out.println("100 not found");
		}
		
	}
}
