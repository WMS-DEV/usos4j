package pl.wmsdev;

import org.junit.jupiter.api.Test;

public class UsosTimeTableAPITest extends UsosAPIBaseTest {

	@Test
	void user() {
		log(userApi.timeTable().user());
	}

	@Test
	void classgroup() {
		log(userApi.timeTable().classgroup("52818", "1"));
	}

	@Test
	void courseEdition() {
		log(userApi.timeTable().courseEdition("W13IST-SI0002G", "2023/24-Z"));
	}
}
