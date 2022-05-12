package fr.grdf.poc.cp.tests;

import fr.grdf.poc.common.*;
import fr.grdf.poc.model.*;
import org.junit.*;
import org.kie.api.runtime.*;

import static org.junit.Assert.*;

public class TestWithClasspath
{
  @Test
  public void testwithClasspath()
  {
    KieContainerUtils kieContainerUtils = new KieContainerUtils();
    KieSession kieSession = kieContainerUtils.createSession("fr.grdf.poc.drools.aa.sf");
    DemandeDTO demandeDTO = new DemandeDTO(TypeStatutDemandeEnum.ATTENTE_ANALYSE);
    kieSession.insert(demandeDTO);
    kieSession.fireAllRules();
    assertEquals(TypeStatutDemandeEnum.CLOTUREE_ANNULEE, demandeDTO.getStatutCourantDemande());
  }
}
