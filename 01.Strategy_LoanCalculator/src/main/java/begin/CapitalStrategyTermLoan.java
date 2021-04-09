package begin;

public class CapitalStrategyTermLoan extends CapitalStrategy {

    @Override
    public double capital(Loan loan) {
        return loan.getCommitment() * loan.duration() * riskFactor(loan);
    }

    @Override
    public double duration(Loan loan) {
        return weightedAverageDuration(loan);
    }
}
