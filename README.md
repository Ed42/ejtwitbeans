ejetty-neo4j    
============

embedded jetty with neo4j  deployed openshift diy cartridge  
  
Inspiration  
https://github.com/openshift/openshift-diy-java-demo   
http://krams915.blogspot.com/  
http://wiki.eclipse.org/Jetty/Tutorial/Embedding_Jetty  
http://www.springsource.org/spring-data/neo4j  
http://www.springsource.org/node/3642  

!!!!!!!!!!!!!git !!!!!!!!!!!!!!!!!!! 
git remote add upstream git@github.com:nsavageJVM/ejettygtwitt.git    

git pull -s recursive -X theirs upstream master // merge files from this repo to openshit repo     

git push  // merged files now on openshift    

git push upstream // openshift scripts will now be in this repo   
git add .  
git commit -m "all"  
git status  
git push  
git remote -v  
git push upstream  
git rm -f       

!!!!!!!!!!!!!mvn !!!!!!!!!!!!!!!!!!!    
mvn clean install    
mvn exec:java -Dexec.mainClass="com.japp.App"   