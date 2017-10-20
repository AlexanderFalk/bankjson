package Settings;

public interface BankInterface {
    String bankName();
    double calculateInterestRate(int customerCreditScore, double loanDuration);

    /**
     *
     * @param interestrate - Used to multiply to the loan amount to generate the refund amount
     * @param loanAmount - The total amount of loan requested.
     * @param years - The number of years the requester wants the loan
     * @return loanAmount with added interest rates times the amount of years
     */
    double refund(double interestrate, double loanAmount, double years);

    /**
     *
     * @param defaultCreditScore - Default Credit Score for the implemented bank
     * @param incrementRate - The increment rate of interest rate for the bank in %
     *                     This value is used to show the increasing interest rate, when
     *                     the customer has a bad credit score
     * @param decrementRate - The decrement rate of the interest rate for the bank in %
     *                     This value is used to show the falling interest rate, when
     *                     the customer has a good creditscore
     * @param customerCreditScore - The customers credit score to compare it with the default
     * @param defaultInterestRate - The default interest rate for the bank. Final, static.
     * @param decreaseValue - The decremented value of the credit score before interest rate correlation
     * @param increaseValue - The incremented value of the credit score before interest rate correlation
     * @return
     */
    static double interestRate(final int defaultCreditScore, final double incrementRate, final double decrementRate,
                               int customerCreditScore, final double defaultInterestRate,
                               final double decreaseValue, double increaseValue,
                               double loanDuration, double loanDurationRate) {

        double interestRate = 0;
        double difference;
        double timesMultiplied;

        // Decrease the interest rate with good credit score (Above default)
        if(customerCreditScore >= defaultCreditScore) {
            // The difference between customer score and the default score
            difference = customerCreditScore - defaultCreditScore;

            // The number of times the decreased interest rate value goes up in the difference
            timesMultiplied = difference / decreaseValue;

            interestRate = defaultInterestRate;
            Math.ceil(timesMultiplied);
            for(int i = 0; i < timesMultiplied; i++) { interestRate /= decrementRate; }
        }

        // Increase the interest rate with bad credit score (Below default)
        if(customerCreditScore < defaultCreditScore) {
            // The difference between customer score and the default score
            difference = defaultCreditScore - customerCreditScore;
            // The number of times the increased interest rate value goes up in the difference
            timesMultiplied = difference / increaseValue;


            interestRate = defaultInterestRate;
            Math.floor(timesMultiplied);
            for(int i = 0; i < timesMultiplied; i++) { interestRate *= incrementRate; }
        }

        for(int duration = 0; duration <= loanDuration; duration++ ) { interestRate /= loanDurationRate; }


        return interestRate;
    }
}
