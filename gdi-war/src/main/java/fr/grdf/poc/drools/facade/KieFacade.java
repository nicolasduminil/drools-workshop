package fr.grdf.poc.drools.facade;

import fr.grdf.poc.common.*;
import fr.grdf.poc.model.*;
import lombok.extern.slf4j.*;
import org.kie.api.management.*;
import org.kie.api.runtime.*;

import javax.annotation.*;
import javax.ejb.*;
import java.io.*;

@Stateless
@Slf4j
public class KieFacade implements KieFacadeLocal, KieFacadeRemote, Serializable
{
  /*@Inject
  @KSession*/
  private KieSession kieSession;

  @PostConstruct
  public void init()
  {
    KieContainerUtils kieContainerUtils = new KieContainerUtils(new GAV("fr.grdf.poc", "gdi-kjar", "1.0-SNAPSHOT"));
    kieSession = kieContainerUtils.createSession("fr.grdf.poc.drools.aa.sf");
  }

  @PreDestroy
  public void destroy()
  {
    kieSession.destroy();
    kieSession.dispose();
  }

  @Override
  public TypeStatutDemandeEnum fireRules(DemandeDTO demandeDTO)
  {
    kieSession.insert(demandeDTO);
    kieSession.fireAllRules();
    return demandeDTO.getStatutCourantDemande();
  }
}
