# ServerSideTechnology
University project



# Tomcat server.xml
  To run propertly add section below into server.xml and set start URL as https://**YOUR_IP**:8443/...
  ```	xml
  <Connector
           protocol="org.apache.coyote.http11.Http11NioProtocol"
           port="8443" maxThreads="200"
           scheme="https" secure="true" SSLEnabled="true"
           keystoreFile="conf/keystore.p12" keystorePass="password"
           clientAuth="false" sslProtocol="TLS"/>
  ```
  
