export domainHome=/home/nicolas/oracle/wls1411/user_projects/domains/base_domain/
#
# Build a clean archive vy skipping unit tests execution
#
#
echo ">>> build.sh: Building a clean archive by skipping unit tests execution"
mvn -DskipTests clean install > /dev/null
echo ">>> build.sh: Done"
#
# If WebLogic Server isn't running already then start it
#
if ps -ef | grep weblogic | grep -v grep > /dev/null
then
  :
else
  echo ">>> build.sh: Starting WebLogic Server"
  mvn -o -pl gdi-war com.oracle.weblogic:weblogic-maven-plugin:start-server -DdomainHome=${domainHome} > /dev/null
  echo ">>> build.sh: Done"
fi
#
# Deploy the gdi-war applications
#
echo ">>> build.sh: Deploying the gdi-war.war application"
mvn -o -pl gdi-war com.oracle.weblogic:weblogic-maven-plugin:redeploy > /dev/null
echo ">>> build.sh: Done"
echo ">>> build.sh: Deploying the gdi-war-dt.war application"
mvn -o -pl gdi-war-dt com.oracle.weblogic:weblogic-maven-plugin:redeploy > /dev/null
echo ">>> build.sh: Done"
#
# Run unit tests
#
echo ">>> build.sh: Running the unit tests"
mvn -DskipITs test
echo ">>> build.sh: Done"
#
# Run integration tests
#
echo ">>> build.sh: Running the integration tests"
mvn -DskipUTs verify
echo ">>> build.sh: Done"
if  [[ $1 = "-c" ]]
then
  #
  # Undeploy gdi-war application
  #
  echo ">>> Undeploying the gdi-war application"
  mvn -o -pl gdi-war com.oracle.weblogic:weblogic-maven-plugin:undeploy > /dev/null
  echo ">>> build.sh: Done"
  #
  # Stop WebLogic Server
  #
  echo ">>> build.sh: Stopping WebLogic Server"
  mvn -o -pl gdi-war com.oracle.weblogic:weblogic-maven-plugin:stop-server -DdomainHome=${domainHome} > /dev/null
  echo ">>> build.sh: Done"
fi
