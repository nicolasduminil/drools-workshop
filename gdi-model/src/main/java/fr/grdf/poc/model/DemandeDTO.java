package fr.grdf.poc.model;

import java.io.*;

public class DemandeDTO implements Serializable
{
  private TypeStatutDemandeEnum statutCourantDemande;

  public DemandeDTO()
  {
  }

  public DemandeDTO(TypeStatutDemandeEnum statutCourantDemande)
  {
    this.statutCourantDemande = statutCourantDemande;
  }

  public TypeStatutDemandeEnum getStatutCourantDemande()
  {
    return statutCourantDemande;
  }

  public void setStatutCourantDemande(TypeStatutDemandeEnum statutCourantDemande)
  {
    this.statutCourantDemande = statutCourantDemande;
  }
}
