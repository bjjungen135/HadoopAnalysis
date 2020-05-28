gradle build
$HADOOP_HOME/bin/hadoop dfs -rm -r /output/stateMonitor
$HADOOP_HOME/bin/hadoop jar build/libs/cs455.hadoop.jar cs455.hadoop.stateMonitor.StateMonitorJob /data/meteorological /data/gases /output/stateMonitor
$HADOOP_HOME/bin/hadoop dfs -rm -r /output/so2Coast
$HADOOP_HOME/bin/hadoop jar build/libs/cs455.hadoop.jar cs455.hadoop.so2Coast.CoastSO2Job /data/gases /output/so2Coast
$HADOOP_HOME/bin/hadoop dfs -rm -r /output/so2TimeOfDay
$HADOOP_HOME/bin/hadoop jar build/libs/cs455.hadoop.jar cs455.hadoop.so2TimeOfDay.SO2TimeOfDayJob /data/gases /output/so2TimeOfDay
$HADOOP_HOME/bin/hadoop dfs -rm -r /output/so2Years
$HADOOP_HOME/bin/hadoop jar build/libs/cs455.hadoop.jar cs455.hadoop.so2Years.SO2YearsJob /data/gases /output/so2Years
$HADOOP_HOME/bin/hadoop dfs -rm -r /output/hottestStates
$HADOOP_HOME/bin/hadoop jar build/libs/cs455.hadoop.jar cs455.hadoop.hottestStates.HottestStatesJob /data/meteorological /output/hottestStates
