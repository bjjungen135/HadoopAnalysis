export HADOOP_CONF_DIR=${HOME}/cs455/hw3/cs455.hadoop/client-config
#$HADOOP_HOME/bin/hadoop dfs -rm -r /home/output/stateMonitor
#$HADOOP_HOME/bin/hadoop jar ./build/libs/cs455.hadoop.jar cs455.hadoop.stateMonitor.StateMonitorJob /data/gases /data/meteorological /home/output/stateMonitor
$HADOOP_HOME/bin/hadoop dfs -rm -r /home/output/so2Coast
$HADOOP_HOME/bin/hadoop jar ./build/libs/cs455.hadoop.jar cs455.hadoop.so2Coast.CoastSO2Job /data/gases /home/output/so2Coast
#$HADOOP_HOME/bin/hadoop dfs -rm -r /home/output/so2TimeOfDay
#$HADOOP_HOME/bin/hadoop jar ./build/libs/cs455.hadoop.jar cs455.hadoop.so2TimeOfDay.SO2TimeOfDayJob /data/gases /home/output/so2TimeOfDay
#$HADOOP_HOME/bin/hadoop dfs -rm -r /home/output/so2Years
#$HADOOP_HOME/bin/hadoop jar ./build/libs/cs455.hadoop.jar cs455.hadoop.so2Years.SO2YearsJob /data/gases /home/output/so2Years
#$HADOOP_HOME/bin/hadoop dfs -rm -r /home/output/hottestStates
#$HADOOP_HOME/bin/hadoop jar ./build/libs/cs455.hadoop.jar cs455.hadoop.hottestStates.HottestStatesJob /data/meteorological /home/output/hottestStates
