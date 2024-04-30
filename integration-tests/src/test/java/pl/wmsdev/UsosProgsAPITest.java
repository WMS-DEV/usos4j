package pl.wmsdev;

import org.junit.jupiter.api.Test;
import pl.wmsdev.usos4j.model.common.UsosLanguage;
import pl.wmsdev.usos4j.model.progs.*;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class UsosProgsAPITest extends UsosAPIBaseTest {

	@Test
	void programme() {
		log(serverApi.progs().programme(UsosProgrammeParam.builder("W04-ISTP-000P-OSIW7").build()));
	}

	@Test
	void programmesNoArguments() {
		assertThrows(IllegalArgumentException.class, () -> {
			log(serverApi.progs().programmes(UsosProgrammesParam.builder().build()));
		});
	}

	@Test
	void programmesSingleArgument() {
		log(serverApi.progs().programmes(UsosProgrammesParam.builder("W04-ISTP-000P-OSIW7").build()));
	}

	@Test
	void programmesMultipleArguments() {
		log(serverApi.progs().programmes(UsosProgrammesParam.builder("W04-ISTP-000P-OSIW7", "W04-SZTP-000P-OSME3", "W04-INAP-CCSA-OSMW3").build()));
	}

	@Test
	void search() {
		log(serverApi.progs().search(UsosProgrammeSearchParam.builder(UsosLanguage.PL).build()));
		log(serverApi.progs().search(UsosProgrammeSearchParam.builder(UsosLanguage.PL).query("geodezja").build()));

	}

	@Test
	void stage() {
		log(serverApi.progs().stage(UsosStageParam.builder("2I-IST-000").build()));
	}

	@Test
	void student() {
		log(userApi.progs().student());
		log(userApi.progs().student().get(0).programme().id());
	}

	@Test
	void studentProgramme() {
		log(userApi.progs().studentProgramme(UsosStudentProgrammeParam.builder("95378").build()));
	}

	@Test
	void studentProgrammesNoArguments() {
		assertThrows(IllegalArgumentException.class, () -> {
			log(userApi.progs().studentProgrammes(UsosStudentProgrammesParam.builder().build()));
		});
	}

	@Test
	void studentProgrammesSingleArgument() {
		log(userApi.progs().studentProgrammes(UsosStudentProgrammesParam.builder("95378").build()));
	}

	@Test
	void studentProgrammesMultipleArguments() {
		log(userApi.progs().studentProgrammes(UsosStudentProgrammesParam.builder("95378", "95379", "95377").build()));
	}
}
