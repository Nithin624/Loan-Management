package service;

import exception.InsufficientAssetException;
import model.Customer;

public class EligibilityService {

    private static final double LOAN_PERCENTAGE = 70.0;

    public double calculateEligibleAmount(Customer customer) {

        double totalAssets =
                customer.getAsset().getTotalAssetValue();

        return totalAssets * LOAN_PERCENTAGE / 100;
    }

    public boolean checkEligibility(
            Customer customer,
            double requestedAmount)
            throws InsufficientAssetException {

        double eligibleAmount =
                calculateEligibleAmount(customer);

        if (requestedAmount > eligibleAmount) {

            throw new InsufficientAssetException(
                    "Loan rejected. Maximum eligible amount is ₹"
                    + eligibleAmount);
        }

        return true;
    }
}
