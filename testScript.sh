export HADOOP_CONF_DIR=${HOME}/cs455/hw3/cs455.hadoop/client-config
$HADOOP_HOME/bin/hdfs dfs -ls /data
$HADOOP_HOME/bin/hdfs dfs -ls /home
$HADOOP_HOME/bin/hdfs jar build/libs/cs455.hadoop.jar cs455.hadoop.stateMonitor.StateMonitorJob /data/gases /data/meteorological /home/output/stateMonitor
