package begin;

public class CapitalStrategyRevolver extends CapitalStrategy {

    @Override
    public double capital(Loan loan) {
        return (loan.outstandingRiskAmount() * loan.duration() * riskFactor(loan))
                + (loan.unusedRiskAmount() * loan.duration() * unusedRiskFactor(loan));
    }

}
