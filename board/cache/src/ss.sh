export CLASSPATH=ojdbc14.jar:cacheServer.jar

ps -ef | grep 'rmiregistry 2010' | awk '{print $2}' |sudo xargs kill
sleep 2
/usr/java/jdk_rmi/bin/rmiregistry 2010 &
ps -ef | grep 'rmiregistry 2011' | awk '{print $2}' |sudo xargs kill
sleep 2
/usr/java/jdk_rmi/bin/rmiregistry 2011 &
ps -ef | grep 'rmiregistry 2012' | awk '{print $2}' |sudo xargs kill
sleep 2
/usr/java/jdk_rmi/bin/rmiregistry 2012 &
ps -ef | grep 'rmiregistry 2013' | awk '{print $2}' |sudo xargs kill
sleep 2
/usr/java/jdk_rmi/bin/rmiregistry 2013 &
ps -ef | grep 'rmiregistry 2014' | awk '{print $2}' |sudo xargs kill
sleep 2
/usr/java/jdk_rmi/bin/rmiregistry 2014 &
ps -ef | grep 'rmiregistry 2015' | awk '{print $2}' |sudo xargs kill
sleep 2
/usr/java/jdk_rmi/bin/rmiregistry 2015 &
ps -ef | grep 'rmiregistry 2016' | awk '{print $2}' |sudo xargs kill
sleep 2
/usr/java/jdk_rmi/bin/rmiregistry 2016 &
ps -ef | grep 'rmiregistry 2017' | awk '{print $2}' |sudo xargs kill
sleep 2
/usr/java/jdk_rmi/bin/rmiregistry 2017 &
ps -ef | grep 'rmiregistry 2018' | awk '{print $2}' |sudo xargs kill
sleep 2
/usr/java/jdk_rmi/bin/rmiregistry 2018 &
ps -ef | grep 'rmiregistry 2019' | awk '{print $2}' |sudo xargs kill
sleep 2
/usr/java/jdk_rmi/bin/rmiregistry 2019 &
ps -ef | grep 'rmiregistry 2020' | awk '{print $2}' |sudo xargs kill
sleep 2
/usr/java/jdk_rmi/bin/rmiregistry 2020 &

ps -ef | grep 'cacheServer.jar 10' | awk '{print $2}' |sudo xargs kill
/usr/java/jdk_rmi/bin/java -Xmx4096m -jar cacheServer.jar 10 &
sleep 150
ps -ef | grep 'cacheServer.jar 11' | awk '{print $2}' |sudo xargs kill
/usr/java/jdk_rmi/bin/java -Xmx2048m -jar cacheServer.jar 11 &
sleep 60
ps -ef | grep 'cacheServer.jar 12' | awk '{print $2}' |sudo xargs kill
/usr/java/jdk_rmi/bin/java -Xmx2048m -jar cacheServer.jar 12 &
sleep 60
ps -ef | grep 'cacheServer.jar 13' | awk '{print $2}' |sudo xargs kill
/usr/java/jdk_rmi/bin/java -Xmx2048m -jar cacheServer.jar 13 &
sleep 60
ps -ef | grep 'cacheServer.jar 14' | awk '{print $2}' |sudo xargs kill
/usr/java/jdk_rmi/bin/java -Xmx2048m -jar cacheServer.jar 14 &
sleep 60
ps -ef | grep 'cacheServer.jar 15' | awk '{print $2}' |sudo xargs kill
/usr/java/jdk_rmi/bin/java -Xmx2048m -jar cacheServer.jar 15 &
sleep 60
ps -ef | grep 'cacheServer.jar 16' | awk '{print $2}' |sudo xargs kill
/usr/java/jdk_rmi/bin/java -Xmx2048m -jar cacheServer.jar 16 &
sleep 60
ps -ef | grep 'cacheServer.jar 17' | awk '{print $2}' |sudo xargs kill
/usr/java/jdk_rmi/bin/java -Xmx2048m -jar cacheServer.jar 17 &
sleep 60
ps -ef | grep 'cacheServer.jar 18' | awk '{print $2}' |sudo xargs kill
/usr/java/jdk_rmi/bin/java -Xmx2048m -jar cacheServer.jar 18 &
sleep 60
ps -ef | grep 'cacheServer.jar 19' | awk '{print $2}' |sudo xargs kill
/usr/java/jdk_rmi/bin/java -Xmx2048m -jar cacheServer.jar 19 &
sleep 60
ps -ef | grep 'cacheServer.jar 20' | awk '{print $2}' |sudo xargs kill
/usr/java/jdk_rmi/bin/java -Xmx2048m -jar cacheServer.jar 20 &
sleep 60

