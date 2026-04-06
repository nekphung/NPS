FROM tomcat:10
COPY dist/*.war /usr/local/tomcat/webapps/ROOT.war
