# gelecex-signer
Tubitak MA3-API kütüphaneleri kullanılarak oluşturulan bir elektronik imza uygulaması.

#### Tubitak ma3-api kütüphaneleri;
Tubitak MA3 (Milli Açık Anahtar Altyapısı) kütüphaneleri proje içerisindeki lib klasörü altında bulunmaktadır. 
Tubitak MA3 kütüphanelerinin güncel versiyonları http://yazilim.kamusm.gov.tr adresinden indirilebilir.

#### 3rd Party Kütüphaneleri Local Maven Repository Eklemek

`mvn install:install-file -Dfile=<jar-dosya-yolu> -DgroupId=<group-id> -DartifactId=<artifact-id> -Dversion=<version> -Dpackaging=pom`

Örnek: 

`mvn install:install-file -Dfile=akiscif-2.5.4.jar -DgroupId=tr.gov.tubitak.bilgem.uekae.akis -DartifactId=akiscif -Dversion=2.5.6 -Dpackaging=pom`

`mvn install:install-file -Dfile=ma3api-asn-2.1.8.jar -DgroupId=tr.gov.tubitak.uekae.esya.api -DartifactId=ma3api-asn -Dversion=2.1.8 -Dpackaging=pom`
