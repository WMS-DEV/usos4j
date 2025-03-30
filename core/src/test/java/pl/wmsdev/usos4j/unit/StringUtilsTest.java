package pl.wmsdev.usos4j.unit;

import org.junit.jupiter.api.Test;
import pl.wmsdev.usos4j.utils.StringUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StringUtilsTest {

    @Test
    void shouldHandleBasicParsing() {
        assertEquals("exam_session_number", StringUtils.camelToSnakeCase("examSessionNumber"));
    }

    @Test
    void shouldHandleSingleLetterWordsParsing() {
        assertEquals("why_cant_i_view_results", StringUtils.camelToSnakeCase("whyCantIViewResults"));
    }
}
