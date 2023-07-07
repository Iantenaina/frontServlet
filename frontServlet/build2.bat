cd ./build/web/WEB-INF/classes
jar cf ./fr.jar ./etu2010
copy "U:\Collab\frontServlet\frontServlet\build\web\WEB-INF\classes\fr.jar" "U:\Collab\frontServlet\testFramework\web\WEB-INF\lib"
cd "U:\Collab\frontServlet\testFramework\build\web"
jar  cf ./testframework.war .
cd "U:\Collab\frontServlet\frontServlet"