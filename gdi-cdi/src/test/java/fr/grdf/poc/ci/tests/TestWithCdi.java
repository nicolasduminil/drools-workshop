package fr.grdf.poc.ci.tests;

import fr.grdf.poc.cdi.*;
import fr.grdf.poc.model.*;
import org.jboss.weld.environment.se.*;
import org.junit.*;

import static org.junit.Assert.*;

public class TestWithCdi
{
  @Test
  public void testRule()
  {
    DroolsWithCdi droolsWithCdi = new Weld().initialize().instance().select(DroolsWithCdi.class).get();
    assertNotNull(droolsWithCdi);
    DemandeDTO demandeDTO = new DemandeDTO(DemandeDTO.TypeStatutDemandeEnum.ATTENTE_ANALYSE);
    assertEquals(DemandeDTO.TypeStatutDemandeEnum.CLOTURE_ANNULEE, droolsWithCdi.applyRules(demandeDTO));
  }
}
