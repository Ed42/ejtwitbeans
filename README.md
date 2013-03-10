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
git remote add upstream git clone ssh://513c5be0e0b8cd9763000365@twittermine-wbdemo.rhcloud.com/~/git/twittermine.git/  

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
local     
mvn exec:java -Dexec.mainClass="com.japp.App"
remote   
ctl_app stop  
cd app-root  
cd repo  
mvn clean install  
to run as fork
(setsid mvn exec:java -Dexec.mainClass="com.japp.App" &)  

local repo ubuntu box   
ssh 513c5be0e0b8cd9763000365@twittermine-wbdemo.rhcloud.com  

http://twittermine-wbdemo.rhcloud.com/

