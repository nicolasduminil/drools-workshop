package fr.grdf.drools.facade.dt;

import fr.grdf.poc.model.*;

import javax.ejb.*;

@Remote
public interface KieFacadeDtRemote
{
  public DemandeDTO.TypeStatutDemandeEnum fireRules (DemandeDTO demandeDTO);
}