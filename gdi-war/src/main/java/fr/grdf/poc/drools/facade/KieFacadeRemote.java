package fr.grdf.poc.drools.facade;

import fr.grdf.poc.model.*;

import javax.ejb.*;

@Remote
public interface KieFacadeRemote
{
  public DemandeDTO.TypeStatutDemandeEnum fireRules(DemandeDTO demandeDTO);
}
