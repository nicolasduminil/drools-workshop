package fr.grdf.poc.ci.tests;

import fr.grdf.poc.common.*;
import fr.grdf.poc.model.*;
import org.junit.*;
import org.kie.api.management.*;
import org.kie.api.runtime.*;

import static org.junit.Assert.*;

public class TestDroolsWithCi
{
  @Test
  public void testwithCi()
  {
    KieContainerUtils kieContainerUtils = new KieContainerUtils(new GAV("fr.grdf.poc", "gdi-kjar", "1.0-SNAPSHOT"));
    KieSession kieSession = kieContainerUtils.createSession("fr.grdf.poc.drools.aa.sf");
    DemandeDTO demandeDTO = new DemandeDTO(DemandeDTO.TypeStatutDemandeEnum.ATTENTE_ANALYSE);
    kieSession.insert(demandeDTO);
    kieSession.fireAllRules();
    assertEquals(DemandeDTO.TypeStatutDemandeEnum.CLOTURE_ANNULEE, demandeDTO.getStatutCourantDemande());
  }
}
