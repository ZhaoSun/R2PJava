package begin;

public class CapitalStrategyAdvisedLine extends CapitalStrategy {

    @Override
    public double capital(Loan loan) {
        return loan.getCommitment() * loan.getUnusedPercentage() * loan.duration() * riskFactor(loan);
    }

    @Override
    public double duration(Loan loan) {
        return yearsTo(loan.getExpiry(), loan);
    }
}
