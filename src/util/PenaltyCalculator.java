package util;

public class PenaltyCalculator {

    private static final double PENALTY_RATE = 5.0;

    public static double calculatePenalty(double loanAmount) {

        return loanAmount * PENALTY_RATE / 100;
    }
}
