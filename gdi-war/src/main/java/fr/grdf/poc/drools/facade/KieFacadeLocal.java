package fr.grdf.poc.drools.facade;

import fr.grdf.poc.model.*;

import javax.ejb.*;

@Local
public interface KieFacadeLocal
{
  public TypeStatutDemandeEnum fireRules (DemandeDTO demandeDTO);
}
