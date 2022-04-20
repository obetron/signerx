# gelecex-signer
gelecex-signer projesi birden fazla modulden olusan vefarkli modullerin imzalamada farkli islemleri ustlendigi bir projedir. 

* signerx-common
* signerx-utils
* signerx-smartcard
* signerx-cades
* signerx-xades
* signerx-pades
* signerx-verification

## signerx-common
signerx-common modulu, model yapisinda basit duzeyde islemlerin yapildigi siniflari icermektedir.

## signerx-utils
signerx-util modulu, teknik olarak projeye altyapi saglayacak islemlerin yapildigi (orn. hashing, I/O, converting) siniflari icermektedir.

## signerx-smartcard
signerx-smartcard modulu, PKCS#11 yapisini kullanarak akilli kartlar ve HSM cihazlarina baglanti saglanmasi icin gerekli siniflari icermektedir.

## signerx-cades
signerx-cades modulu, cades ES_BES'ten baslayarak, ES_A tipine kadar imza atabilen kodlari icermektedir.

## signerx-pades
signerx-pades modulu, pades BES'ten LTV tipine kadar imza atabilen kodlari icermektedir.

## signerx-xades
signerx-xades modulu, xades ES_BES'ten ES_A tipine kadar imza atabilen kodlari icermektedir.

## signerx-verification
signerx-verfication modulu, imzanin dogrulama sonuclarini hesaplayan kodlari icermektedir.

## signerx-encryption
signerx-encryption modulu, simetrik sifreleme islemlerinin yapildigi kodlari icermektedir.