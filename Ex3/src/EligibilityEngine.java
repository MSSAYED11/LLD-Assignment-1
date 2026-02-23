import java.util.*;

public class EligibilityEngine {
    private final FakeEligibilityStore store;
    private final List<EligibilityRule> rules = List.of(
            new DisciplinaryRule(),
            new CgrRule(),
            new AttendanceRule(),
            new CreditsRule());

    public EligibilityEngine(FakeEligibilityStore store) {
        this.store = store;
    }

    public void runAndPrint(StudentProfile s) {
        ReportPrinter p = new ReportPrinter();
        EligibilityEngineResult r = evaluate(s); // giant conditional inside
        p.print(s, r);
        store.save(s.rollNo, r.status);
    }

    public EligibilityEngineResult evaluate(StudentProfile s) {

        List<String> reasons = new ArrayList<>();

        for (EligibilityRule rule : rules) {
            String r = rule.check(s);
            if (r != null) {
                reasons.add(r);
                return new EligibilityEngineResult("NOT_ELIGIBLE", reasons);
            }
        }

        return new EligibilityEngineResult("ELIGIBLE", reasons);
    }
}

class EligibilityEngineResult {
    public final String status;
    public final List<String> reasons;

    public EligibilityEngineResult(String status, List<String> reasons) {
        this.status = status;
        this.reasons = reasons;
    }
}
