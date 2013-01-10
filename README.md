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
git remote add upstream ssh://99ee496f6d4847b2a62fe03a81e1e032@ejtwitbeans-democode.rhcloud.com/~/git/ejtwitbeans.git/    

git pull -s recursive -X theirs upstream master // merge files from this repo to openshit repo     

git push upstream // merged files now on openshift    

git push  // openshift scripts will now be in this repo   
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