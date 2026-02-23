import java.util.*;

public class OnboardingService {

    private final StudentRepository db;
    private final OnboardingPrinter printer = new OnboardingPrinter();

    public OnboardingService(StudentRepository db){
        this.db = db;
    }

    public void registerFromRawInput(String raw){

        printer.printInput(raw);

        StudentParser parser = new StudentParser();
        Map<String,String> kv = parser.parse(raw);

        StudentValidator validator = new StudentValidator();
        List<String> errors = validator.validate(kv);

        if (!errors.isEmpty()){
            printer.printErrors(errors);
            return;
        }

        String name = kv.getOrDefault("name","");
        String email = kv.getOrDefault("email","");
        String phone = kv.getOrDefault("phone","");
        String program = kv.getOrDefault("program","");

        String id = IdUtil.nextStudentId(db.count());
        StudentRecord rec = new StudentRecord(id,name,email,phone,program);

        db.save(rec);

        printer.printSuccess(rec, db.count());
    }
}