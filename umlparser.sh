classpath=$1
pngfileName=$2
java -cp . Parser $classpath
cd copied-files
javadoc -docletpath /usr/local/lib/UmlGraph.jar -doclet org.umlgraph.doclet.UmlGraph -private *.java
dot -Tpng -o$classpath/$pngfileName.png graph.dot
