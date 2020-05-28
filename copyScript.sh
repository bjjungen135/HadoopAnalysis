export HADOOP_CONF_DIR=${HOME}/cs455/hw3/cs455.hadoop/client-config
$HADOOP_HOME/bin/hdfs dfs -cat /data/gases/hourly_42401_2000.csv > hourly_42401_2000.csv
