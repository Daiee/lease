# docker 环境命令
 redis
```shell
docker run --name redis -p 6379:6379 -d redis redis-server --bind 0.0.0.0
```
 mysql
```shell
docker run --name mysql -e MYSQL_ROOT_PASSWORD=Xuyc7791. -p 3306:3306 -d  mysql
```
 minio
```shell
// minio
docker run --name minio -p 9000:9000 -p 9001:9001 -e "MINIO_ROOT_USER=daie" -e "MINIO_ROOT_PASSWORD=12345678" -v /data:/data minio/minio server /data --console-address ":9001"
```