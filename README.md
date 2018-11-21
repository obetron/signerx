# gelecex-signer
Tubitak MA3-API kütüphaneleri kullanılarak oluşturulan bir elektronik imza uygulaması.

#### Tubitak ma3-api kütüphaneleri;
İlgili kütüphaneler ve örnek kodlar http://yazilim.kamusm.gov.tr adresinden indirilebilir. 

#### 3rd Party Kütüphaneleri Local Maven Repository Eklemek

`mvn install:install-file -Dfile=akiscif-2.5.4.jar -DgroupId=tr.gov.tubitak.bilgem.uekae.akis -DartifactId=akiscif -Dversion=2.5.6 -Dpackaging=pom`
`mvn install:install-file -Dfile=asn1rt-6.6.4.jar -DgroupId=com.objsys.asn1j.runtime -DartifactId=asn1rt -Dversion=6.6.4 -Dpackaging=pom`
`mvn install:install-file -Dfile=ma3api-asic-2.1.8.jar -DgroupId=tr.gov.tubitak.uekae.esya.api -DartifactId=ma3api-asic -Dversion=2.1.8 -Dpackaging=pom`
`mvn install:install-file -Dfile=ma3api-asn-2.1.8.jar -DgroupId=tr.gov.tubitak.uekae.esya.api -DartifactId=ma3api-asn -Dversion=2.1.8 -Dpackaging=pom`
`mvn install:install-file -Dfile=ma3api-certstore-2.1.8.jar -DgroupId=tr.gov.tubitak.uekae.esya.api -DartifactId=ma3api-certstore -Dversion=2.1.8 -Dpackaging=pom`
`mvn install:install-file -Dfile=ma3api-certvalidation-2.1.8.jar -DgroupId=tr.gov.tubitak.uekae.esya.api -DartifactId=ma3api-certvalidation -Dversion=2.1.8 -Dpackaging=pom`
`mvn install:install-file -Dfile=ma3api-cmssignature-2.1.8.jar -DgroupId=tr.gov.tubitak.uekae.esya.api -DartifactId=ma3api-cmssignature -Dversion=2.1.8 -Dpackaging=pom`
`mvn install:install-file -Dfile=ma3api-common-2.1.8.jar -DgroupId=tr.gov.tubitak.uekae.esya.api -DartifactId=ma3api-common -Dversion=2.1.8 -Dpackaging=pom`
`mvn install:install-file -Dfile=ma3api-crypto-2.1.8.jar -DgroupId=tr.gov.tubitak.uekae.esya.api -DartifactId=ma3api-crypto -Dversion=2.1.8 -Dpackaging=pom`
`mvn install:install-file -Dfile=ma3api-crypto-gnuprovider-2.1.8.jar -DgroupId=tr.gov.tubitak.uekae.esya.api -DartifactId=ma3api-crypto-gnuprovider -Dversion=2.1.8 -Dpackaging=pom`
`mvn install:install-file -Dfile=ma3api-crypto-sunprovider-2.1.8.jar -DgroupId=tr.gov.tubitak.uekae.esya.api -DartifactId=ma3api-crypto-sunprovider -Dversion=2.1.8 -Dpackaging=pom`
`mvn install:install-file -Dfile=ma3api-infra-2.1.8.jar -DgroupId=tr.gov.tubitak.uekae.esya.api -DartifactId=ma3api-infra -Dversion=2.1.8 -Dpackaging=pom`
`mvn install:install-file -Dfile=ma3api-signature-2.1.8.jar -DgroupId=tr.gov.tubitak.uekae.esya.api -DartifactId=ma3api-signature -Dversion=2.1.8 -Dpackaging=pom`
`mvn install:install-file -Dfile=ma3api-smartcard-2.1.8.jar -DgroupId=tr.gov.tubitak.uekae.esya.api -DartifactId=ma3api-smartcard -Dversion=2.1.8 -Dpackaging=pom`
`mvn install:install-file -Dfile=ma3api-xmlsignature-2.1.8.jar -DgroupId=tr.gov.tubitak.uekae.esya.api -DartifactId=ma3api-xmlsignature -Dversion=2.1.8 -Dpackaging=pom`
