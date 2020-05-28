function usage {
cat << EOF

	Usage: run.sh -[ 1 ] -c -s

	-1 : Basic Question

	-c : Compile
	-s : Shared HDFS

EOF
	exit 1
}

function hadoop_runner {
$HADOOP_HOME/bin/hadoop fs -rm -R ${OUT_DIR}/${CLASS_JOB} ||: \
&& $HADOOP_HOME/bin/hadoop jar build/libs/${JAR_FILE} cs455.hadoop.${CLASS_JOB}.WordCountJob \
$FIRST_INPUT $SECOND_INPUT ${OUT_DIR}/${CLASS_JOB} \
&& $HADOOP_HOME/bin/hadoop fs -ls ${OUT_DIR}/${CLASS_JOB} \
$$ $HADOOP_HOME/bin/hadoop fs -head ${OUT_DIR}/${CLASS_JOB}/part-r-00000
}

if [[ $# -lt 1 ]]; then
	usage;
fi

if [[ $* = *-c* ]]; then
	find ~/.gradle -type f -name "*.lock" | while read f; do rm $f; done
	gradle build

	LINES=`find . -name "*.java" -print | xargs wc -l | grep "total" | awk '{$1=$1};1'`

	echo Project has "$LINES" lines
fi

if [[ $* = *-s* ]]; then
	export HADOOP_CONF_DIR=${HOME}/hadoop/client-config
	HDFS_DATA="data"
	OUTDIR="/home/out"
else
	HDFS_DATA="local"
	OUT_DIR="/out"
fi

JAR_FILE="cs455.hadoop.jar"
FIRST_INPUT="/"${HDFS_DATA}"/gases/hourly_42401_1980.csv"
SECOND_INPUT="/"${HDFS_DATA}"/meteorological/"

case "$1" in

-1) CLASS_JOB="wordcount"
    SECOND_INPUT=""
    hadoop_runner
    ;;

*) usage;
    ;;

esac
