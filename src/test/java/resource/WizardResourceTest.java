package resource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;

import org.junit.ClassRule;
import org.junit.Test;

import com.codahale.metrics.MetricRegistry;

import de.fb.demo.entity.WIZARD_HAT;
import de.fb.demo.entity.Wizard;
import de.fb.demo.resource.WizardResource;
import de.fb.demo.store.WizardStore;
import de.fb.demo.view.WizardView;
import io.dropwizard.testing.junit.ResourceTestRule;
import io.dropwizard.views.ViewMessageBodyWriter;
import io.dropwizard.views.ViewRenderer;
import io.dropwizard.views.mustache.MustacheViewRenderer;
import jersey.repackaged.com.google.common.collect.Sets;

public class WizardResourceTest {

	// store is so lightweight, no need for a mock
	private static WizardStore store = new WizardStore();

	private static Iterable<ViewRenderer> renderer = Sets.<ViewRenderer>newHashSet(new MustacheViewRenderer());

	@ClassRule
	public static final ResourceTestRule resources = ResourceTestRule.builder() //
			.addResource(new WizardResource(store)) //
			.addProvider(new ViewMessageBodyWriter(new MetricRegistry(),
					renderer )) //
			.build();


	@Test
	public void testGetPongWizard() {
		Wizard wizard = resources.client().target("/wizards/ping").request().get(Wizard.class);
		assertThat(wizard).isEqualTo(WizardResource.PONG_WIZARD);

	}

	@Test
	public void testWizardRoundtrip() {

		Wizard wizard = new Wizard("Roundtrip-Wizard", WIZARD_HAT.MYSTIC_HAT, "+49 (0) 40 23224223");

		resources.client().target("/wizards").request().post(Entity.entity(wizard, MediaType.APPLICATION_JSON));

		Wizard wizardReturned = resources.client().target("/wizards/" + "Roundtrip-Wizard").request().get(Wizard.class);

		assertEquals(wizard, wizardReturned);

	}

	@Test
	public void testGetWizardView() {
		String name = "ViewWizard";
		Wizard wizard = new Wizard(name, WIZARD_HAT.MYSTIC_HAT, "+49 (0) 40 23224223");
		store.store(wizard);

		String viewUrl = "/wizards/ViewWizard/view";
		
		WizardView wizardView = resources.client().target(viewUrl).request()
				.accept(MediaType.APPLICATION_JSON).get(WizardView.class);
		assertEquals(wizard, wizardView.getWizard());

		//same URL different accept type
		String html = resources.client().target(viewUrl).request().accept(MediaType.TEXT_HTML)
				.get(String.class);

		assertThat(html).contains("<html");

		assertThat(html).contains("ViewWizard");

	}
}
