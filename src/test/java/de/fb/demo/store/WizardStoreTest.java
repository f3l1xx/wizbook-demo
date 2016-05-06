package de.fb.demo.store;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.annotation.JsonFormat.Feature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import de.fb.demo.entity.WIZARD_HAT;
import de.fb.demo.entity.Wizard;

public class WizardStoreTest {

	WizardStore wizardStore;

	@Before
	public void setup() {
		wizardStore = new WizardStore();
	}

	
	@Test
	public void testStore() throws Exception {
		//given
		assertFalse(wizardStore.get("Test").isPresent());
		
		//when
		Wizard wizard = new Wizard("Test", WIZARD_HAT.MYSTIC_HAT, "phone");
		wizardStore.store(wizard);
		
		//then
		assertEquals(wizard, wizardStore.get("Test").get());
	}
	
	@Test
	public void testStart() throws Exception {
		// given
		wizardStore.stop();
		assertFalse(wizardStore.isRunning());
		
		// when
		wizardStore.start();
		
		// then
		assertTrue(wizardStore.isRunning());
	}

	@Test
	public void testStop() throws Exception {
		// given
		wizardStore.start();
		assertTrue(wizardStore.isRunning());
		
		// when
		wizardStore.stop();
		
		// then
		assertFalse(wizardStore.isRunning());
	}

}
