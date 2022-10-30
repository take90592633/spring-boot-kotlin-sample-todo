# バージョン
- Java: 17  
- MySQL: 8.0.30

# Docker MySQL起動方法
```
docker-compose up -d
```

# Docker MySQL再作成
```
docker-compose down
rm -rf docker/mysql/data
docker-compose up -d
```

# ローカル起動(初回起動時)
Docker MySQLを起動してMySQLにログイン。(以下コマンド実行後にパスワード入力)
```
mysql -h 127.0.0.1 -u admin -p -D step2 
```
以下DDLを実行する
```
src/main.resources/mysql/init.sql
```

# ローカル起動
- Docker MySQLを起動する 
- 環境変数に以下を設定する。
  - AWS_S3_BUCKET=S3のバケット名
  - AWS_S3_FILE_PATH=アップロード元のフォルダパス
  - AWS_SES_MAIL=SESに登録したメールアドレス
  - AWS_ACCESS_KEY_ID=AWSアクセスキーID
  - AWS_SECRET_ACCESS_KEY=AWSシークレットアクセスキー