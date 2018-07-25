svr_name=$1;
version=$2
cd $1;
mvn clean package docker:build -DskipTests;
docker tag registry.cn-hangzhou.aliyuncs.com/kayb/$svr_name registry.cn-hangzhou.aliyuncs.com/kayb/$svr_name:$2

docker push registry.cn-hangzhou.aliyuncs.com/kayb/$svr_name:latest
docker push registry.cn-hangzhou.aliyuncs.com/kayb/$svr_name:$2