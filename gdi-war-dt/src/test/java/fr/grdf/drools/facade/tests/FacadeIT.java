package fr.grdf.drools.facade.tests;

import fr.grdf.drools.facade.dt.*;
import fr.grdf.poc.common.*;
import fr.grdf.poc.model.*;
import org.junit.*;

import javax.naming.*;

import static org.junit.Assert.*;

public class FacadeIT
{
  private static KieFacadeDtRemote kieFacadeRemote;
  private static final String jndiName = "java:global.gdi-war-dt.KieFacadeDt!fr.grdf.drools.facade.dt.KieFacadeDtRemote";

  @BeforeClass
  public static void before() throws Exception
  {
    kieFacadeRemote = (KieFacadeDtRemote) new InitialContext().lookup(jndiName);
  }

  @Test
  public void givenRemoteKieIsNotNullThenSucceed()
  {
    assertNotNull(kieFacadeRemote);
  }

  @Test
  public void givenAttenteAnalyseWhenFireThenClotureAnnulee()
  {
    DemandeDTO demandeDTO = new DemandeDTO(DemandeDTO.TypeStatutDemandeEnum.ATTENTE_ANALYSE);
    assertEquals(DemandeDTO.TypeStatutDemandeEnum.CLOTURE_ANNULEE, kieFacadeRemote.fireRules(demandeDTO));
  }

  @Test
  public void givenExcelFileThenGenerateDrl()
  {
    String drl = new KieContainerUtils().getDrlFromExcel("fr/grdf/poc/drools/AaEgdAnnulerDemande.xls");
    assertNotNull(drl);
    System.out.println ("### DRL: " + drl);
  }
}
