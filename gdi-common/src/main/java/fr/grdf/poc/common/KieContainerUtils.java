package fr.grdf.poc.common;

import lombok.*;
import lombok.extern.slf4j.*;
import org.drools.decisiontable.*;
import org.kie.api.*;
import org.kie.api.builder.*;
import org.kie.api.io.*;
import org.kie.api.management.*;
import org.kie.api.runtime.*;
import org.kie.internal.builder.*;
import org.kie.internal.io.*;

@Getter
@Setter
@Slf4j
public class KieContainerUtils
{
  private KieServices kieServices;
  private KieContainer kieContainer;
  private GAV gav;

  public KieContainerUtils()
  {
    this.kieServices = KieServices.Factory.get();
    this.kieContainer = kieServices.getKieClasspathContainer();
  }

  public KieContainerUtils(GAV gav)
  {
    this.kieServices = KieServices.Factory.get();
    this.gav = gav;
    this.kieContainer = kieServices.newKieContainer(kieServices.newReleaseId(gav.getGroupId(), gav.getArtifactId(), gav.getVersion()));
  }

  public KieBase getKieBase(String name)
  {
    return kieContainer.getKieBase(name);
  }

  public KieSession createDefaultSession()
  {
    compile();
    return kieContainer.newKieSession();
  }

  public KieSession createSession(String name)
  {
    compile();
    return kieContainer.newKieSession(name);
  }

  private Results compile()
  {
    Results results = kieContainer.verify();
    if (results.hasMessages(Message.Level.WARNING, Message.Level.ERROR))
    {
      results.getMessages().forEach(ex -> log.info(">>> KieContainerUtils.loadRulesFromArtifacts: Cannot compile rules from {}:{}:{} maven artifact. \t{}", gav.getGroupId(), gav.getArtifactId(), gav.getVersion(), ex.getLevel(), ex.getText()));
      throw new IllegalStateException("Compilation errors were found. Check the logs.");
    }
    return results;
  }

  public String getDrlFromExcel(String excelFile)
  {
    DecisionTableConfiguration configuration = KnowledgeBuilderFactory.newDecisionTableConfiguration();
    configuration.setInputType(DecisionTableInputType.XLS);
    return new DecisionTableProviderImpl().loadFromResource(ResourceFactory.newClassPathResource(excelFile, getClass()), configuration);
  }
}
