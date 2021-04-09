package begin;

import java.util.Date;
import java.util.Iterator;

public class CapitalStrategy {

    private static final long MILLIS_PER_DAY = 86400000;
    private static final long DAYS_PER_YEAR = 365;

    //////////////////// 贷款金额周期计算 ////////////////////
    // 贷款金额计算
    public double capital(Loan loan) {
        // 有效日为空，到期日不为空，为定期贷款
        // 资金计算方法：承诺金额 * 期限 * 风险因素
        if (loan.getExpiry() == null && loan.getMaturity() != null)
            return loan.getCommitment() * loan.duration() * riskFactor(loan);

        // 有效日不为空，到期日为空，为循环贷款或建议信用额度贷款
        // 若未用份额不为 100%，为信用额度贷款，否则为循环贷款
        if (loan.getExpiry() != null && loan.getMaturity() == null) {
            if (loan.getUnusedPercentage() != 1.0)
                // 信用额度贷款
                // 资金计算方法：承诺金额 * 未用份额 * 期限 * 风险因素
                return loan.getCommitment() * loan.getUnusedPercentage() * loan.duration() * riskFactor(loan);
            else
                // 循环贷款
                // 资金计算方法：未清风险
                return (loan.outstandingRiskAmount() * loan.duration() * riskFactor(loan))
                        + (loan.unusedRiskAmount() * loan.duration() * unusedRiskFactor(loan));
        }
        return 0.0;
    }

    // 贷款周期计算
    public double duration(Loan loan) {
        if (loan.getExpiry() == null && loan.getMaturity() != null) 		// 定期贷款
            return weightedAverageDuration(loan);
        else if (loan.getExpiry() != null && loan.getMaturity() == null) 	// 循环或建议信用额度贷款
            return yearsTo(loan.getExpiry(), loan);
        return 0.0;
    }

    // 获取风险因素
    private double riskFactor(Loan loan) {
        return RiskFactor.getFactors().forRating(loan.getRiskRating());
    }

    // 获取未使用风险因素
    private double unusedRiskFactor(Loan loan) {
        return UnusedRiskFactors.getFactors().forRating(loan.getRiskRating());
    }

    // 加权平均周期
    private double weightedAverageDuration(Loan loan) {
        double duration = 0.0;
        double weightedAverage = 0.0;
        double sumOfPayments = 0.0;

        Iterator<Payment> loanPayments = loan.getPayments().iterator();
        while (loanPayments.hasNext()) {
            Payment payment = loanPayments.next();
            sumOfPayments += payment.amount();
            weightedAverage += yearsTo(payment.date(), loan) * payment.amount();
        }

        if (loan.getCommitment() != 0.0)
            duration = weightedAverage / sumOfPayments;

        return duration;
    }

    // 年数差
    private double yearsTo(Date endDate, Loan loan) {
        Date beginDate = (loan.getToday() == null ? loan.getStart() : loan.getToday());
        return ((endDate.getTime() - beginDate.getTime()) / MILLIS_PER_DAY) / DAYS_PER_YEAR;
    }

}
