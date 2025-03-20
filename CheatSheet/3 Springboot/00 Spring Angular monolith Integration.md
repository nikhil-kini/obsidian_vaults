Note:
Any `index .html, .css, .js` files in the path `src/main/resources/static` will be served when you start a springboot application
**This is not recommended**, use `S3` and `cloudfront` or a  `reverse proxy` instead of spring application

https://marmo.dev/angular-with-java - for details 
https://github.com/marco76/SpringAngularDemo/tree/feature/war-for-tomcat - original code

Woking project - https://github.com/nikhil-kini/SpringAngularDemo
## To Build the project

- maven goal - clean install
- go to Delivery module `/target/`
```sh
java -jar MyBeautifulApp-0.0.2-SNAPSHOT.jar
```
- localhost:8080 to view the project

## Docker build

go to project root folder
```sh
docker buildx build -t demo .
```

```sh
docker run -p 8080:8080 demo
```

## Refer this to enable reload functionality 

[https://stackoverflow.com/questions/74569409/spring-boot-2-7-5-angular-15-as-a-single-war](https://stackoverflow.com/questions/74569409/spring-boot-2-7-5-angular-15-as-a-single-war)