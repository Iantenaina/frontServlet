cd ./build/web/WEB-INF/classes
jar cf ./fr.jar ./etu2010
copy "C:\Users\itu\Documents\projet github\frontServlet\frontServlet\build\web\WEB-INF\classes\fr.jar" "C:\Users\itu\Documents\projet github\frontServlet\testFramework\web\WEB-INF\lib"
cd "C:\Users\itu\Documents\projet github\frontServlet\testFramework\build\web"
jar  cf ./testframework.war .
copy "C:\Users\itu\Documents\projet github\frontServlet\testFramework\build\web\testframework.war" "C:\Program Files\Apache Software Foundation\Apache Tomcat 8.0.27\webapps"