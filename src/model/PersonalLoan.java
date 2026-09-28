package model;

public class PersonalLoan extends Loan {

    public PersonalLoan(int loanId,
                        int customerId,
                        double principalAmount,
                        double interestRate,
                        int durationMonths,
                        double penalty) {

        super(loanId,
              customerId,
              principalAmount,
              interestRate,
              durationMonths,
              penalty);
    }

    @Override
    public double calculateInterest() {

        double years = getDurationMonths() / 12.0;

        return (getPrincipalAmount()
                * getInterestRate()
                * years) / 100;
    }
}