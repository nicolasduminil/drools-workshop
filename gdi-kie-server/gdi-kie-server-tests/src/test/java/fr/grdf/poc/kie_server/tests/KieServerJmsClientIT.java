package fr.grdf.poc.kie_server.tests;

import org.jboss.arquillian.container.test.api.*;
import org.jboss.arquillian.junit.*;
import org.jboss.shrinkwrap.api.*;
import org.jboss.shrinkwrap.api.spec.*;
import org.junit.*;
import org.junit.runner.*;
import org.kie.server.api.model.*;
import org.kie.server.client.*;

import javax.jms.*;
import javax.naming.*;
import java.io.*;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
@Ignore
public class KieServerJmsClientIT
{
  private static final String REQUEST_QUEUE_JNDI = "jms/queue/KIE.SERVER.REQUEST";
  private static final String RESPONSE_QUEUE_JNDI = "jms/queue/KIE.SERVER.RESPONSE";
  private static final String CONNECTION_FACTORY = "weblogic.jms.ConnectionFactory";

  @Deployment
  public static WebArchive deployKieServer() throws Exception
  {
    return ShrinkWrap.createFromZipFile(WebArchive.class, new File("../gdi-kie-server-war/target/gdi-kie-server-war-1.0-SNAPSHOT-custom.war"));
  }

  @Test
  @RunAsClient
  public void runSimpleRules() throws Exception
  {
    Context context = new InitialContext();
    QueueConnectionFactory queueConnectionFactory = (QueueConnectionFactory)context.lookup(CONNECTION_FACTORY);
    assertNotNull(queueConnectionFactory);
    Queue requestQueue = (Queue) context.lookup(REQUEST_QUEUE_JNDI);
    assertNotNull(requestQueue);
    Queue responseQueue = (Queue) context.lookup(RESPONSE_QUEUE_JNDI);
    assertNotNull(responseQueue);
    KieServicesConfiguration config = KieServicesFactory.newJMSConfiguration(
      queueConnectionFactory, requestQueue, responseQueue, "nicolas", "California1");
    assertNotNull(config);
    KieContainerResource kContainer = new KieContainerResource(new ReleaseId("fr.grdf.poc", "gdi-kjar", "1.0-SNAPSHOT"));
    assertNotNull(kContainer);
    KieServicesClient client = KieServicesFactory.newKieServicesClient(config);
    assertNotNull(client);
    ServiceResponse<KieContainerResource> resp = client.createContainer("my-deploy", kContainer);
    assertNotNull(resp);
    assertEquals(KieContainerStatus.STARTED, resp.getResult().getStatus());
    //Server is now available to receive requests
  }
}
