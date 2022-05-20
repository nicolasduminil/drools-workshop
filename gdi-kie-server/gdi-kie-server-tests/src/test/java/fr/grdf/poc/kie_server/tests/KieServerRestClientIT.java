package fr.grdf.poc.kie_server.tests;

import org.jboss.arquillian.container.test.api.*;
import org.jboss.arquillian.junit.*;
import org.jboss.shrinkwrap.api.*;
import org.jboss.shrinkwrap.api.spec.*;
import org.junit.*;
import org.junit.runner.*;
import org.kie.server.api.model.*;
import org.kie.server.client.*;

import java.io.*;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public class KieServerRestClientIT
{
  @Deployment
  public static WebArchive deployKieServer() throws Exception
  {
    return ShrinkWrap.createFromZipFile(WebArchive.class, new File("../gdi-kie-server-war/target/gdi-kie-server-war-1.0-SNAPSHOT-custom.war"));
  }

  @Test
  @RunAsClient
  public void runSimpleRules() throws Exception
  {
    KieServicesConfiguration config = KieServicesFactory.newRestConfiguration(
      "http://localhost:7001/gdi-kie-server-war-1.0-SNAPSHOT-custom/services/rest/server",
      "kie-user", "California1", 10000);
    KieServicesClient client = KieServicesFactory.newKieServicesClient(config);
    KieContainerResource kContainer = new KieContainerResource("my-deploy", new ReleaseId("fr.grdf.poc", "gdi-kjar", "1.0-SNAPSHOT"));
    ServiceResponse<KieContainerResource> resp = client.createContainer("my-deploy", kContainer);
    assertNotNull(resp);
    KieContainerResource kcr = resp.getResult();
    assertNotNull(kcr);
    KieContainerStatus kis = kcr.getStatus();
    assertNotNull(kis);
    assertTrue(kis.equals(KieContainerStatus.STARTED) || kis.equals((KieContainerStatus.CREATING)));
    //Server is now available to receive requests
  }
}
