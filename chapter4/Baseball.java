package chapter4;

/**
 * Created by bbencze on 2017.06.23..
 */
public class Baseball {

    public static void main(String[] args) {
        int earnedRuns = 3;
        int inningsPitched = 6;
        int inningsInAGame = 9;
        float leagueAverageEra = 4.25f;
        float era = ((float)earnedRuns / (float)inningsPitched) *
                inningsInAGame;
        boolean betterThanAverage;
        if (era < leagueAverageEra) {
            betterThanAverage = true;
        } else {
            betterThanAverage = false;
        }
        char yesNo;
        if (betterThanAverage) {
            yesNo = 'Y';
        } else {
            yesNo = 'N';
        }
        System.out.println("Earned Runs\t\t" + earnedRuns);
        System.out.println("Innings Pitched\t\t" + inningsPitched);
        System.out.println("ERA\t\t\t" + era);
        System.out.println("League Average ERA\t"+leagueAverageEra);
        System.out.println("Is player better than league average "+
                yesNo);
    }

}

