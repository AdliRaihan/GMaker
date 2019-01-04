# GMaker

<h2> Status program : sudah dapat digunakan namun ceritanya masih <i>early-access</i></h2>

  [ENG] i'm currently unemployed and if you have some interest about this project and me... :D you can download my CV <a href="https://drive.google.com/file/d/1aZu-g-dcCRRNkIuIkw8UY6iOQNatvor9/view?usp=sharing">Here</a> . 
  <br><br>
  [IDN]
 Jika anda mencari seorang programmer android , saya sangat tertarik untuk mengisi posisi tersebut maupun Web App Developer , CV saya dapat didownload <a href="https://drive.google.com/file/d/1aZu-g-dcCRRNkIuIkw8UY6iOQNatvor9/view?usp=sharing">disini</a>


<h6>
Markdown masih berupa beberapa informasi tentang pengembangan aplikasi ini.<br>
<b>highlight</b><br>
<a href="https://github.com/Thibobs/GMaker/tree/master/app/src/main/java/later/corporation/adliraihan/gmaker">Mysql Source Code</a><br>
<a href="https://github.com/Thibobs/GMaker/tree/master/app/src/main/java/later/corporation/adliraihan/gmaker/firebase">Firebase source Code</a><br>
<a href="https://github.com/Thibobs/GMaker/tree/master/app/src/main/java/later/corporation/adliraihan/gmaker/adapter">adapter</a><br>
<b>Algorithm Logic</b> made by me<br>
<a href="https://github.com/Thibobs/GMaker/blob/master/app/src/main/java/later/corporation/adliraihan/gmaker/firebase/CreateDailyFunctionTimer.kt">Daily Timer Validation</a><br>
<a href="https://github.com/Thibobs/GMaker/blob/master/app/src/main/java/later/corporation/adliraihan/gmaker/firebase/">Calendar picker & Time difference Algorithm</a><br>
<a href="https://github.com/Thibobs/GMaker/blob/master/app/src/main/java/later/corporation/adliraihan/gmaker/firebase/">Background services for reminding ongoing schedule</a><br>
  <b>Features</b><br>
  - Almost all activity are userfriendly , ada beberapa fitur yg mempermudah user dalam menggunakan app ini<br>
  - Reminder<br>
  - Realtime database's<br>
  - etc<br>
</h6><br><br>

  <b>Latest Application Version : 1.0.0.5 <i> Masih tahap pengembangan & Hotfix</i></b><br>
  Download Here <a href="https://drive.google.com/open?id=1UUeeY85cbfOyiGq5a7be5L2AY8ZO7V1I">Here</a><br><br>
  <b>Daftar bug yang sudah diatasi</b> :<br>
 1. Muatan service membuat error seluruh aplikasi / Banyak Intent Stopped Working . <br>
 2. Aplikasi tidak dapat membuka agenda yang sudah expired / Crash .<br>
3.  Memoryleaking <br><br>
  <b>New Feature Added</b> :<Br>
  Notifications "stacking" <br>
  <img src="aplikasi_image/UpdateDadakan.jpg" width="256px"><br>
  <b>Note:</b>
  stacking notification sudah secara programmatically dan data diambil melalui database , source code versi terbaru belum bisa saya upload , ini hanya hotfix karena pada versi sebelumnya aplikasi sama sekali tidak bisa digunakan.<br><br>
  <hr/>
  <b>Old Version</b><br>
  Version 1 : <a href="https://drive.google.com/file/d/1qJd5-rkZunbi-uSx3f0LNGeYgkPpgjd0/view">sini</a> <i>Versi Github</i><br>
  
  <h5>Tested on Xiaomi Redmi A4</h5>
  <br>
  <i>Latest Github Source Code Update : 04/January/2019</i>
  <ul>
  <li>Notifications</li>
  <li>Xiaomi Bugfix</li>
  </ul>

<table>
  <tr>
  <td><img src="aplikasi_image/NotificationUpdate_1.jpg" width="128px"></td>
  <td><img src="aplikasi_image/NotificationUpdate_2.jpg" width="128px"></td>
  </tr>
  <tr>
    <td colspan="2">Notification akan muncul dan memiliki sifat <i>Unremovable</i> jika ada agenda pada hari yang ditentukan. <i>detail activity tersebut masih dalam pengembangan design</i></td>
  </tr>
  </table>

<i>seluruh image dapat dilihat  <a href="https://github.com/Thibobs/GMaker/tree/master/aplikasi_image">disini</a>

Developingnya sampai saat ini baru 30%.
email saya adliraihan002@gmail.com

<h2>OLD Dec 30, 2018</h2>
<h6>
<table>
  <tr>
    <td>Final schedule Design</td>
    <td>Notification</td>
    <td>Using Background services</td>
  </tr>
  
  <tr>
  <td><img src="aplikasi_image/ServicesNotification.png" width="64px"></td>
  <td><img src="aplikasi_image/notification_1.png" width="64px"></td>
  <td><img src="aplikasi_image/notificationwithServices.png" width="64px"></td>
  </tr>
  
  <tr>
    <td>Update ini sudah termasuk implementasi schedule terhadap database <i>untuk nonpremium user max schedule dibatasi hingga 3 schedule , sedangkan premium dapat membuat schedule sampai 10</td>
  <td>implementasi notification | <b>next update</b> : mengimplementasikannya dengan coding , yaitu ongoing schedule/agenda</td>
  <td>Implementasi background services .</td>
  </tr>
  </table>
<hr/>
<table>
  <tr>
    <td><s>Next Update is in progress</s> <b>Sudah di implementasikan pada update yang baru</b></td>
  </tr>
  <tr>
    <td><img src="aplikasi_image/1546094694914.jpg" width="64px"></td>
  </tr>
  <tr>
    <td>
      Penjelasan Update :<br>
      Menambah fitur Scheduling dengan pancuan waktu (dari) dan (sampai) ,<br>
      contoh : <br>
         1. 08:00 AM - 09:00 AM | Membeli Ayam <br>
         2. 09:00 AM - 10:00 PM | Bekerja <br>
         3. etc<br>
      Meskipun form hanya satu , tetapi bila user menambahkan schedule time pada tanggal dan bulan serta tahun yang sama
      maka system akan melakukan appending . Tetapi apabila waktu yang di tentukan telah lewat maka schedule tersebut
      akan dihapus oleh system .  
      <br><br>
      Masukan / saran bisa menghubungi  saya melalui email. 
    </td>
  </tr>
</table>
<hr/>
<i><b>Seluruh image yang terdapat pada applikasi ini seluruhnya dari unsplash.com</b></i>
<br>
Bila anda penasaran proses pembuatan secara menyeluruh bisa di cek di IG saya <a href="https://www.instagram.com/adli.raihan/">disini</a>
dari mulai pertama pembuatan sampai akhir serta beberapa design yang di rancang ulang / diubah . Rencananya app ini pula akan diijadikan opensource <i> well it is lol </i>
<br><br>

<b>Future update</b> :
- <b>Diary update </b> , setelah schedule dilakukan / seperti mengirim kesan dan pesan setelah schedule tersebut di lakukan.
- <s><b>Schedule update</b> perjam notification akan muncul setiap jam 10 malam (<i>secara default</i>) sebagai reminder untuk mengisi schedule esok hari.</s> <i>sudah terimplementasi</i>
<br><br>
<center>
<table>
  <tr>
    <td colspan="2">form membuat agenda</td>
  </tr>
  <tr>
    <td><img src="aplikasi_image/4.jpg" width="128px"></td>
    <td><img src="aplikasi_image/25438.jpg" width="128px"></td>
  </tr>
</table>
</center>
</h6>
