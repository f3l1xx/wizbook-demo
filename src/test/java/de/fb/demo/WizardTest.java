package de.fb.demo;

import static io.dropwizard.testing.FixtureHelpers.fixture;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.UUID;

import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import de.fb.demo.entity.WIZARD_HAT;
import de.fb.demo.entity.Wizard;
import io.dropwizard.jackson.Jackson;

public class WizardTest {

	private static final ObjectMapper MAPPER = Jackson.newObjectMapper();

	@Test
	public void serializesToJSON() throws Exception {

		Wizard wizard = new Wizard("DaGreatestWizard", WIZARD_HAT.MYSTIC_HAT, "00234-232",
				UUID.fromString("fa025745-f2f1-4c4b-ac6b-f80f6b23d53c"));

		final String expected = MAPPER
				.writeValueAsString(MAPPER.readValue(fixture("fixtures/wizard.json"), Wizard.class));

		assertThat(MAPPER.writeValueAsString(wizard)).isEqualTo(expected);
	}

	@Test
	public void deserializesFromJSON() throws Exception {

		Wizard wizard = new Wizard("DaGreatestWizard", WIZARD_HAT.MYSTIC_HAT, "00234-232",
				UUID.fromString("fa025745-f2f1-4c4b-ac6b-f80f6b23d53c"));

		assertThat(MAPPER.readValue(fixture("fixtures/wizard.json"), Wizard.class)).isEqualTo(wizard);
	}

}
