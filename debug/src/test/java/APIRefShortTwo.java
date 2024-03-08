import lombok.Getter;

import java.util.List;
@Getter
public class APIRefShortTwo {

    String name;
    List<APIRefResultTwo> result_fields;

    @Getter
    static class APIRefResultTwo {
        String name;
    }
}
