# Case Study :: URL Encoding

![Technology - Spring Boot](https://img.shields.io/badge/Technology-Spring_Boot-blue)
![Tracing Difficulty - Easy](https://img.shields.io/badge/Tracing_Difficulty-Easy-green)
![Implementation Difficulty - Easy](https://img.shields.io/badge/Implementation_Difficulty-Easy-green)

## The Condition

You are developing an application where there is a specific GET endpoint, that needs to accept data. Due to this, you cannot use Request Body to pass the data, so that you are using alternatives.

## The Problem

Currently, the endpoint accepts the data, via URL parameters. But since the data is formatted in json, it could cause encoding error.

## The Objective

Fix the method of data passing, to allow the GET endpoint to accept data. There is multiple way to do it.

## The Expected Result

The GET endpoint could accept the data.

## Lesson Learned Case Study
Berdasarkan studi kasus yang diberikan terkait URL Encoding, kami mendapat banyak temuan baru, ilmu ilmu baru terkait permasalahan yang terdapat pada studi kasus tersebut.

Tentunya saat mencoba code yang diberikan oleh P79, kami mendapat satu issue pertama yaitu project tidak dapat dilakukan running. sehingga langkah awal kami yaitu mencari cara agar project dapat dilakukan running terlebih dahulu. setelah melakukan eksplorasi ternyata terdapat perbedaan dari versi JDK itu sendiri yang dimana rata rata kelompok kami menggunakan versi JDK 11, dan yang digunakan pada project itu sendiri merupakan JDK versi 17, maka langkah yang kami ambil yaitu mengganti terlebih dahulu versi JDK kami menjadi 17, dan issue pertama pun berhasil kami selesaikan. 
Selanjutnya setelah kami dapat melakukan running pada project, kami mencoba memasukan url berikut "http://localhost:8080/sample?parameters={"param1":"value1","param2":"value2","param3":42,"param4":3.14}" yang dimana di url tersebut kami mempassing json didalamnya. barulah kami mendapat issue kedua, dimana issue tersebut berisi status HTTP 400 - Bad Request saat running. kami mencoba eksplorasi terlebih dahulu apa yang menyebabkan hal itu terjadi yang dimana dapat memunculkan status HTTP 400 - Bad Requestnya. setelah melakukan eksplorasi kami mendapat jawaban jika hal tersebut terjadi karena pada url terdapat special character yang menyebabkan status tersebut. setelah mengetahui apa yang menjadi penyebab issue kedua ini, kami lalu mencoba mencari cara bagaimana untuk melakukan solve pada issue kedua ini, kami mendapat solusi pertama yaitu dengan memasukan URL seperti ini "http://localhost:8082/sample?parameters=%7B"param1":"val1","param2":"val2","param3":10,"param4":20.5%7D", dan yang terjadi yaitu tidak muncul issue baru dan json yang terdapat pada url dapat dimunculkan, yang kami anggap langkah tersebut dapat mengatasi issue kedua, dimana letak perubahan yang ada yaitu kami mengganti suatu kurawal pada url menjadi "%7B" dan "%7D" hal tersebut menyelesaikan issue kedua ini dan DoD pun dapat tercapai.
Tetapi setelah itu kami tidak cepat berpuas setelah Dod nya tercapai, kami mencoba untuk mencari solusi atau alternatif lain untuk dapat mensolve issue kedua ini, dan setelah dilakukan eksplorasi lagi, kami mendapat cara lain dimana kita dapat melakukan konfigurasi melalui file "application.properties". konfigurasi yang dilakukan adalah dengan parameter berikut "server.tomcat.relaxed-path-chars" parameter tersebut merupakan parameter yang mengontrol karakter karakter yang diizinkan dalam path URL yang diterima oleh server tomcat.
Untuk kesimpulannya kami mendapat dua cara untuk dapat mensolve issue kedua tersebut yaitu yang pertama dengan mengganti spesial character nya dan yang kedua yaitu melalui konfigurasi "application.properties".
Lalu selanjutnya setelah Dod kami anggap tercapai, kami melakukan eksplorasi untuk dapat mendeploy aplikasi ke dalam virtual machine. issue pertama pada saat mendeploy pun kami temukan, yaitu java nya tidak bisa dilakukan running pada VM, kami mengganggap jika java belum terinstall pada virtual machine yang ada. lalu langkah kami untuk dapat mengatasi permasalahan tersebut yaitu melakukan setup environment pada virtual machine untuk dapat siap digunakan. 
setelah set up enviroment kami mencoba menjalankan aplikasi pada virtual machine, barulah kami mendapat issue kedua yaitu terdapat error saat mengakses controller tertentu yaitu sebagai contoh error saat mengakses /sample, dan kami beranggapan jika konfigurasi server untuk tomcat tidak terbaca.
