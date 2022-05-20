package fr.grdf.drools.facade.dt;

import fr.grdf.poc.model.*;

import javax.ejb.*;

@Local
public interface KieFacadeDtLocal
{
  public DemandeDTO.TypeStatutDemandeEnum fireRules (DemandeDTO demandeDTO);
}
