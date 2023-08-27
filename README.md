# Amadeus Case Study
Bu proje, Spring Boot tabanlı bir uygulamayı içermektedir. Aşağıda proje bileşenleri ve yapılandırma detayları verilmiştir.

## Bileşenler
Proje aşağıdaki bileşenleri içermektedir:

- Controller: HTTP isteklerini yöneten sınıfların bulunduğu bölümdür.
- Model: Veritabanı varlıklarını ve veri yapılarını tanımlayan sınıfların bulunduğu bölümdür.
- Repository: Veritabanı işlemlerini yöneten sınıfların bulunduğu bölümdür.
- ScheduledJob: Belirli bir zaman aralığında çalışan görevleri yöneten sınıfların bulunduğu bölümdür.
- Service: İş mantığını uygulayan sınıfların bulunduğu bölümdür.
- Config: Uygulama yapılandırmalarının bulunduğu dosyadır.

## Swagger UI
Uygulamanın Swagger UI arayüzüne aşağıdaki linkten erişebilirsiniz: [Link](http://localhost:8080/swagger-ui/index.html)

## Scheduled Job
Uygulamada aşağıdaki Scheduled Job bulunmaktadır:

```
@Autowired
public FlightScheduledJob(FlightRepository flightRepository, AirportRepository airportRepository, RestTemplate restTemplate) {
    // ...
}

@Scheduled(cron = "0 * * * * ?")
public void fetchAndSaveFlightData() {
    // ...
}
```
Bu görev, belirli bir zaman aralığında uçuş verilerini çekip veritabanına kaydetmektedir.
Bu isteğin yapıldığı endpoint şöyledir:
## MockApiController Sınıfı

`MockApiController` sınıfı, sahte uçuş verileri oluşturan bir API sunar. Bu sınıf, "/mock-api" yolu altında uçuş verilerini döndüren bir RESTful API sağlar.

### Endpoint: /flights

- **HTTP Method:** GET
- **Yol:** `/mock-api/flights`
- **Açıklama:** Bu endpoint, sahte uçuş verileri içeren bir liste döndürür. Her bir uçuş verisi, rasgele değerlerle oluşturulur ve uçuş numarası, kalkış havaalanı, varış havaalanı, kalkış tarihi ve saati, dönüş tarihi ve saati, fiyat gibi bilgiler içerir. Bu endpoint örnek uçuş verilerini döndürerek uygulamanın test edilmesini sağlar.

Örnek istek: GET /mock-api/flights




## Docker Compose
Proje için Docker Compose dosyası aşağıdaki gibi yapılandırılmıştır:
```
version: '3.3'

services:
  db:
    # ...
  phpmyadmin:
    # ...
 ```
Bu Docker Compose dosyası, bir MySQL veritabanı ve PhpMyAdmin arayüzünü içermektedir.

Dockerfile
Projenin Dockerfile'ı aşağıdaki gibidir:

```
FROM amazoncorretto:17-alpine
ARG JAR_FILE=target/*.jar
EXPOSE 8080
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
```
Bu Dockerfile, uygulamayı bir Amazon Corretto 17 tabanlı bir Docker konteynerinde çalıştırmak için kullanılır.

