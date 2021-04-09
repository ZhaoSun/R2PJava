package begin;

public class CapitalStrategyAdvisedLine extends CapitalStrategy {

    @Override
    public double capital(Loan loan) {
        return loan.getCommitment() * loan.getUnusedPercentage() * loan.duration() * riskFactor(loan);
    }
}
