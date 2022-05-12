package fr.grdf.poc.cdi;

import fr.grdf.poc.model.*;
import lombok.extern.slf4j.*;
import org.kie.api.cdi.*;
import org.kie.api.runtime.*;

import javax.inject.*;

@Slf4j
public class DroolsWithCdi
{
  @Inject
  @KSession
  private KieSession kieSession;
  private DemandeDTO demandeDTO;

  public TypeStatutDemandeEnum applyRules (DemandeDTO demandeDTO)
  {
    kieSession.insert(demandeDTO);
    kieSession.fireAllRules();
    return demandeDTO.getStatutCourantDemande();
  }
}
