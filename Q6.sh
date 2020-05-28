export HADOOP_CONF_DIR=${HOME}/cs455/hw3/cs455.hadoop/client-config
$HADOOP_HOME/bin/hadoop dfs -rm -r /home/output/meanSO2
$HADOOP_HOME/bin/hadoop jar ./build/libs/cs455.hadoop.jar cs455.hadoop.meanSO2.MeanSO2Job /data/gases /home/output/meanSO2
