desc "Install in clojars repository"
task :clojars do
  sh 'rm -rf *.jar pom.xml classes target && lein pom && lein jar'
  sh "cp target/provided/*.jar ."
  sh 'scp pom.xml *.jar clojars@clojars.org:'
end

