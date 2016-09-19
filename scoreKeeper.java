import java.util.Arrays;
/**
 * Customer Service Reports progam keeps track of the ratings representatives 
 * of a business have recieved. 
 * 
 * Authors: Natie Elkaim, Judah Diament
 * Version: 9.17.2016
 */
public class CustomerServiceRepScores
{
	private int repQuantity;
	private int numberOfPossibleScores;
	private int[][] scores;
	private int[][] lastTwenty;
	private boolean[] doingBad;
	
	/**
	 * The main method of the Customer Service Reports progam.
	 * Establish the parameters of the study.
	 * int repQuantity = the number of representatives.
	 * int numberOfPossibleScores = the number of scores in your survey.
	 */
	public CustomerServiceRepScores (int repQuantity, int scoreQuantity) 
	{
		this.repQuantity = repQuantity; //
		this.numberOfPossibleScores = scoreQuantity;
		this.scores = new int[this.repQuantity][this.numberOfPossibleScores];
		for(i = 0; i < this.scores.length; i++)
		{
			Arrays.fill(this.scores[i], 0);
		}
		this.lastTwenty = new int[this.repQuantity][20];
		for(i = 0; i < this.lastTwenty.length; i++)
		{
			Arrays.fill(this.lastTwenty[i], 0);
		}
		this.doingBad = new int[this.repQuantity];
		for(i = 0; i < scores.length; i++)
		{
			Arrays.fill(this.doingBad, false);
		}
	}
	
	/**
	 * For editing the score of a given representative
	 */
	public void editScore(int repID, int score, int amount) 
	{
		this.scores[repID-1][score-1] = amount;
		addToLastTwenty(repID, score, amount);
		checkAverage(int repID);
	}
	
	public int[] getCumulativeScoreForRep(int repID) 
	{
		return Arrays.copyOf(scores[repID], numberOfPossibleScores);
	}
	
	/**
	 * Keep track of the last twenty scores added for a given
	 * representative.
	 */
	public void addToLastTwenty(int repID, int score, int amount) 
	{
		int[] temp = new int[20];
		for(int i = 0; i < 20-amount; i++)
		{
			temp[i+amount] = lastTwenty[repID-1][i];
		}
		for(int i = 0; i < amount; i++)
		{
			temp[i] = score;
		}
		lastTwenty[repID-1] = temp;
	}	
	
	public double getLastTwentyAvg(int repID) 
	{
		int avg = 0;
		for(int i = 0; i < 20; i++)
		{
			avg += lastTwenty[repID-1][i];				
		}
		return avg /= 20;
	}
	
	/**
	 * This method is for quality control. Will inform
	 * the user when a representative's average has dropped 
	 * below a certain point.
	 */
	public void checkAverage(int repID)
	{
		if(doingBad[repID-1] == false && getLastTwentyAverage() <= 2.5)
		{
			doingBad[repID-1] = true;
			System.out.println("representative " + repID + 
			"'s service rating has dropped to " + getAverage() + ".");
		}
	}			
}