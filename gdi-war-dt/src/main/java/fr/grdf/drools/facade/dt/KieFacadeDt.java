package fr.grdf.drools.facade.dt;

import fr.grdf.poc.model.*;
import org.kie.api.*;
import org.kie.api.builder.*;
import org.kie.api.runtime.*;
import org.kie.internal.io.*;

import javax.annotation.*;
import javax.ejb.*;
import java.io.*;

@Stateless
public class KieFacadeDt implements KieFacadeDtLocal, KieFacadeDtRemote, Serializable
{
  private KieSession kieSession;
  
  @PostConstruct
  public void init()
  {
    KieServices kieServices = KieServices.Factory.get();
    KieBuilder kieBuilder = kieServices.newKieBuilder(kieServices.newKieFileSystem().write(ResourceFactory.newClassPathResource("fr/grdf/poc/drools/AaEgdAnnulerDemande.xls", getClass())));
    kieBuilder.buildAll();
    kieSession = kieServices.newKieContainer(kieServices.getRepository().getDefaultReleaseId()).newKieSession();
  }

  @Override
  public DemandeDTO.TypeStatutDemandeEnum fireRules(DemandeDTO demandeDTO)
  {
    kieSession.insert(demandeDTO);
    kieSession.fireAllRules();
    return demandeDTO.getStatutCourantDemande();
  }

  @PreDestroy
  public void destroy()
  {
    kieSession.destroy();
    kieSession.dispose();
  }
}
