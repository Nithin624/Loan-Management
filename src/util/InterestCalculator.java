package util;

public class InterestCalculator {

    public static double calculateSimpleInterest(
            double principal,
            double rate,
            int months) {

        double years = months / 12.0;

        return (principal * rate * years) / 100;
    }
}
