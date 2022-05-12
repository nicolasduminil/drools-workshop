package fr.grdf.poc.model;

import java.io.*;
import java.util.*;

public class ContexteDTO implements Serializable
{
  private TypeEvenementEnum typeEvtIn;
  private List<TypeActionEnum> listeAction = new ArrayList<>();

  public ContexteDTO()
  {
  }

  public ContexteDTO(TypeEvenementEnum typeEvtIn, int nbActivationFired, List<TypeActionEnum> listeAction)
  {
    this.typeEvtIn = typeEvtIn;
    this.listeAction = listeAction;
  }

  public TypeEvenementEnum getTypeEvtIn()
  {
    return typeEvtIn;
  }

  public void setTypeEvtIn(TypeEvenementEnum typeEvtIn)
  {
    this.typeEvtIn = typeEvtIn;
  }

  public List<TypeActionEnum> getListeAction()
  {
    return listeAction;
  }

  public void setListeAction(List<TypeActionEnum> listeAction)
  {
    this.listeAction = listeAction;
  }

  public void addAction (TypeActionEnum typeActionEnum)
  {
    this.listeAction.add(typeActionEnum);
  }
}
