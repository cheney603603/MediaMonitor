HADOOP_CMD="/opt/hadoop-3.2.1/bin/hadoop"
STREAM_JAR_PATH="/opt/hadoop-3.2.1/share/hadoop/tools/lib/hadoop-streaming-3.2.1.jar"
INPUT_FILE_PATH_1="/Data/C3-Art/Art.txt"
OUTPUT_PATH="/Data_splited/C3-Art/"
$HADOOP_CMD fs -rmr -skipTrash $OUTPUT_PATH

# Step 1.
$HADOOP_CMD jar $STREAM_JAR_PATH \
   -input $INPUT_FILE_PATH_1 \
	-output $OUTPUT_PATH \
	-mapper "python mapper.py" \
	-reducer "python reducer.py" \
	-jobconf "mapred.reduce.tasks=3" \
	-file ./mapper.py \
	-file ./reducer.py \
