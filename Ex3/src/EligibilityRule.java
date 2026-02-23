public interface EligibilityRule {
    String check(StudentProfile s); 
    // return null if pass, or reason string if fail
}