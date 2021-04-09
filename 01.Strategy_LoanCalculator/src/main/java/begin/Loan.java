package begin;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Loan {

    private final CapitalStrategy capitalStrategy;
    private Date expiry;                // 有效日
    private Date maturity;                // 到期日
    private Date start;                // 起始日
    private Date today;                // 当日

    private double commitment;            // 承诺金额
    private double outstanding;        // 未清金额
    private double unusedPercentage;    // 未用份额
    private int riskRating;            // 风险评级

    private List<Payment> payments;    // 支付记录

    //////////////////// 对象构造相关 ////////////////////
    // 构造函数
    /* 1. private constructor 2. too many parameter with close type near each other */
    private Loan(double commitment, double outstanding, Date start, Date expiry, Date maturity, int riskRating) {
        this.commitment = commitment;
        this.outstanding = outstanding;
        this.start = start;
        this.expiry = expiry;
        this.maturity = maturity;
        this.riskRating = riskRating;

        this.unusedPercentage = 1.0;
        this.payments = new LinkedList<Payment>();
        capitalStrategy = new CapitalStrategy();
    }

    // 创建定期贷款
    public static Loan newTermLoan(double commitment, Date start, Date maturity, int riskRating) {
        return new Loan(commitment, commitment, start, null, maturity, riskRating);
    }

    // 创建信用额度贷款
    public static Loan newAdvisedLine(double commitment, Date start, Date expiry, int riskRating) {
        if (riskRating > 3)
            return null;

        Loan advisedLine = new Loan(commitment, 0, start, expiry, null, riskRating);
        advisedLine.setUnusedPercentage(0.1);

        return advisedLine;
    }

    // 创建循环贷款
    public static Loan newRevolver(double commitment, Date start, Date expiry, int riskRating) {
        return new Loan(commitment, 0, start, expiry, null, riskRating);
    }

    //////////////////// 贷款金额周期计算 ////////////////////
    // 贷款金额计算
    public double capital() {
        return capitalStrategy.capital(this);
    }

    // 贷款周期计算
    public double duration() {
        return capitalStrategy.duration(this);
    }

    //////////////////// 贷款金额周期计算辅助方法 ////////////////////
    // 未用份额
    double getUnusedPercentage() {
        return unusedPercentage;
    }

    private void setUnusedPercentage(double unusedPercentage) {
        this.unusedPercentage = unusedPercentage;
    }

    // 未清金额
    double outstandingRiskAmount() {
        return outstanding;
    }

    // 未用风险金额
    double unusedRiskAmount() {
        return (commitment - outstanding);
    }

    ////////////////////贷款支付方法 ////////////////////
    // 支付
    public void payment(double amount, Date date) {
        payments.add(new Payment(amount, date));
    }

    public Date getExpiry() {
        return expiry;
    }

    public void setExpiry(Date expiry) {
        this.expiry = expiry;
    }

    public Date getMaturity() {
        return maturity;
    }

    public void setMaturity(Date maturity) {
        this.maturity = maturity;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getToday() {
        return today;
    }

    public void setToday(Date today) {
        this.today = today;
    }

    public double getCommitment() {
        return commitment;
    }

    public void setCommitment(double commitment) {
        this.commitment = commitment;
    }

    public double getOutstanding() {
        return outstanding;
    }

    public void setOutstanding(double outstanding) {
        this.outstanding = outstanding;
    }

    public int getRiskRating() {
        return riskRating;
    }

    public void setRiskRating(int riskRating) {
        this.riskRating = riskRating;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }
}
