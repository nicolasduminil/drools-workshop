package fr.grdf.poc.drools.facade.tests.it;

import fr.grdf.poc.drools.facade.*;
import fr.grdf.poc.model.*;
import org.junit.*;

import javax.naming.*;

import static org.junit.Assert.*;

public class DroolsIT
{
  private static KieFacadeRemote kieFacadeRemote;
  private static final String jndiName = "java:global.gdi-war.KieFacade!fr.grdf.poc.drools.facade.KieFacadeRemote";

  @BeforeClass
  public static void before() throws Exception
  {
    kieFacadeRemote = (KieFacadeRemote) new InitialContext().lookup(jndiName);
  }

  @Test
  public void testKieFacade()
  {
    assertNotNull(kieFacadeRemote);
  }

  @Test
  public void testRules()
  {
    DemandeDTO demandeDTO = new DemandeDTO(DemandeDTO.TypeStatutDemandeEnum.ATTENTE_ANALYSE);
    assertEquals(DemandeDTO.TypeStatutDemandeEnum.CLOTURE_ANNULEE, kieFacadeRemote.fireRules(demandeDTO));
  }
}
