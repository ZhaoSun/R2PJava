package begin;

public class CapitalStrategyRevolver extends CapitalStrategy {

    @Override
    public double capital(Loan loan) {
        return (loan.outstandingRiskAmount() * loan.duration() * riskFactor(loan))
                + (loan.unusedRiskAmount() * loan.duration() * unusedRiskFactor(loan));
    }

    @Override
    public double duration(Loan loan) {
        return yearsTo(loan.getExpiry(), loan);
    }

}
