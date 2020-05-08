M&T KARGO	Version 1.0	03/05/2020

			GEZGİN KARGO PROJESİ

PROJE TANIMI:
--------------------------------------------------------------------------------------
	Bir satıcının bulunduğu şehirden başlayarak her şehre sadece bir kez
uğradıktan sonra başladığı şehre dönebilmesi için en kısa yolun bulunmasıdır.
Bu problem polinom zamanda çozülmesi mümkün olmadığından NP (nonpolynomial problem)
sınıfına girmektedir. Bu projedeki problem, graph yapısı kullanılarak yeni 
geliştirdiğimiz bir algoritma ile "35 ± 10" saniyenin altında çözümlenmiştir. 
--------------------------------------------------------------------------------------

GELİŞTİRME ORTAMI:
--------------------------------------------------------------------------------------
	Proje Windows platformunda JAVA dili ve swing kütüphanesi kullanılarak 
yazılmış olup; Netbeans ortamında, javac kullanılarak derlenip JVM aracılığı ile
test edilmiştir.
--------------------------------------------------------------------------------------

KULLANIM NOTLARI:
--------------------------------------------------------------------------------------
0-	Uygulama jar dosyası ile çalıştırılır. Eğer bir geliştirme ortamı üzerinde
çalıştırılmak istenirse; Main metodu "Arayüz.java" sınıfının içinde olup,
çalıştırmak için bu sınıf "main class" olarak belirlenmelidir. 

1-	Daha hızlı sonuçlar için bilgisayarınızı performans modunda kullanmanız
tavsiye edilmektedir.

2.1-	Uygulama çalıştırıldığında "giriş arayüzü" kullanıcıyı karşılar. Bu ekranda
şehirler listelenmiş olup, kullanıcıdan teslimat şehirlerini seçmesi istenmektedir.
Eğer yanlış bir seçim yapılırsa, seçimin ticki kaldırılıp, kaldığı yerden
devam edilebilir. 

2.2-	Başlangıç şehri uygulama tarafından atandığı için kullanıcı
başlangıç şehrini teslimat şehri olarak seçemez, seçmeye çalışırsa bu işlem uygulama
tarafından engellenir ve kullanıcının karşısına uyarı paneli çıkar.

2.3-	Uygulama en az 3 şehre teslimat yapmak üzere tasarlanmıştır bu yüzden kullanıcı
üç adetten az şehir seçip uygulamayı başlatırsa bu işlem uygulama tarafından
engellenir ve kullanıcının karşısına uyarı paneli çıkar.

2.4-	Kullanıcı uygulamanın gereksinimleri nedeniyle 10 adetten fazla şehir seçemez,
seçmeye çalışırsa bu işlem uygulama tarafından engellenir ve kullanıcı uyarı alır.

3-	Teslimat şehirleri seçildikten sonra arayüzün sol alt kısmında bulunan başla 
butonuna basılır. Bu işlemden sonra rotalar kısa bir süre içerisinde hesaplanır ve
arayüzün sağ alt kısmında en az maliyetli olacak şekilde sıralanır.

4-	Kullanıcı listelenmiş rotalardan aktif olanlarını harita üzerinde çizdirebilir.
Her bir rotanın sağ tarafında çizdirme butonu bulunmaktadır. Kullanıcı aynı anda
birden fazla rotayı harita üzerinde çizdirebilir. Çizdirilen bu rotalar ayrı
pencerelerde kullanıcının karşısına çıkar.	

5-	Kullanıcının karşısına çıkan harita ekranında şehirlerin merkezleri siyah daire
ile belirtilmiş ve şehirler arası beyaz çizgiler kullanılmıştır. Teslimat şehirleri
ise kırmızı daire ile vurgulanmıştır.

6-	Kullanıcı rotayı text olarak görmek isterse harita ekranının en üstünde bulunan
"Rotayı Göster" butonunu kullanır. Bu işlem kullanıcının karşısına mesaj olarak çıkar.

7-	Kullanıcı uygulamayı kapatmadan yeni bir simülasyon başlatabilir.
Arayüzde istenilen şehirler eklenilip çıkarıldıktan sonra 3. adımdan devam edilir.

8-	Programdan çıkmak için arayüz penceresinin kapatılması yeterlidir.
---------------------------------------------------------------------------------------


Müberra ÇELİK - 180202102 - muberraceliik@gmail.com
Taha Batuhan TÜRK - 180202007 - tbturkk@gmail.com
