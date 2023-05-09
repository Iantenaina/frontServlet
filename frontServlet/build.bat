
cd ./build/web/WEB-INF/classes
jar cf ./fr.jar ./etu2010
copy "C:\projet github\frontServlet\frontServlet\build\web\WEB-INF\classes\fr.jar"
 "C:\projet github\frontServlet\testFramework\web\WEB-INF\lib"
cd "C:\projet github\frontServlet\testFramework\build\web"
jar  cf ./testframework.war .
copy "C:\projet github\frontServlet\testFramework\build\web\testframework.war" 
"C:\Program Files\Apache Software Foundation\apache-tomcat-8.5.87"