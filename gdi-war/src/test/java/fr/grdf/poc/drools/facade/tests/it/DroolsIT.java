package fr.grdf.poc.drools.facade.tests.it;

import fr.grdf.poc.drools.facade.*;
import fr.grdf.poc.model.*;
import org.junit.*;

import javax.naming.*;
import java.util.*;

import static org.junit.Assert.*;

public class DroolsIT
{
  private static KieFacadeRemote kieFacadeRemote;
  private static final String jndiName = "java:global.gdi-war.KieFacade!fr.grdf.poc.drools.facade.KieFacadeRemote";

  @BeforeClass
  public static void before() throws Exception
  {
    kieFacadeRemote = (KieFacadeRemote) new InitialContext().lookup("java:global.gdi-war.KieFacade!fr.grdf.poc.drools.facade.KieFacadeRemote");
  }

  @Test
  public void testKieFacade()
  {
    assertNotNull(kieFacadeRemote);
  }

  @Test
  public void testRules()
  {
    DemandeDTO demandeDTO = new DemandeDTO(TypeStatutDemandeEnum.ATTENTE_ANALYSE);
    assertEquals(TypeStatutDemandeEnum.CLOTUREE_ANNULEE, kieFacadeRemote.fireRules(demandeDTO));
  }
}
