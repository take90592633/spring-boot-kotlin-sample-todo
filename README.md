# バージョン
java: 17
MySQL: 8.0.30

# docker MySQL起動方法
```
docker-compose up -d
```

# docker MySQL再作成
```
docker-compose down
rm -rf docker/mysql/data
docker-compose up -d
```