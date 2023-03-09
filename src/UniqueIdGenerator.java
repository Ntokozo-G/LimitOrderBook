import java.util.UUID;

public class UniqueIdGenerator {

    public String getId() {
        return UUID.randomUUID().toString();
    }
}
