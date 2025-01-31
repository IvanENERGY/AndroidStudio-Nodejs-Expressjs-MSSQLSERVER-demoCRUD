<h1>Overview</h1>
<p>[project_android_todo.png]</p>

![project_android_todo](https://github.com/IvanENERGY/AndroidStudio-Nodejs-Expressjs-MSSQLSERVER-demoCRUD/assets/90034836/ac56d5f9-7e0f-4af4-856e-edf92e58c21a)
<h1>Project Introduction</h1>
<p>This is a demo CRUD applicaton Build using </p>
<table>
<tbody>
  <tr>
    <th>Frontend</th>
    <td> Android Studio (JAVA) </td>
  </tr>
  <tr>
    <th rowspan="2">Backend</th>
    <td> NodeJS & Express JS</td>
  </tr>
  <tr>
    <td> MS SQL Server</td>
  </tr>
</tbody>
</table>
<p>The frontend android application use Retrofit Library for calling APIS</p>
<p>The backend follows MVC pattern and preform CRUD on the Task Object in database </p>
<p>Script for Creation of Task Table and Script for stored procedures performing CRUD on Task  are located in <i> /dbscript folder</i> </p>
<p>Database user configuration:</p>
<img width="1628" alt="dbUserSettings" src="https://github.com/IvanENERGY/ReactNative-Nodejs-Expressjs-MSSQLSERVER-demoCRUD/assets/90034836/5a601f58-1f2b-4afd-ad75-b6463a431e60">
<img width="956" alt="dbUserSettings2" src="https://github.com/IvanENERGY/ReactNative-Nodejs-Expressjs-MSSQLSERVER-demoCRUD/assets/90034836/684e1f2a-eacd-40fb-859e-abac2b54de2d">
<p>The fullstack application supports the following functionalities:</p>
<ul>
<li>Basic CRUD operation</li>
</ul>
<h1>Screenshots</h1>
<h2>&#128308;READ</h2>
<span>[android1.png]</span><br>
<img width="265" alt="android1" src="https://github.com/IvanENERGY/AndroidStudio-Nodejs-Expressjs-MSSQLSERVER-demoCRUD/assets/90034836/6af65baa-cef0-46bc-86c1-00f32d7d006d"><br>
<h2>&#128308;CREATE</h2>
<span>[android2.png]</span><br>
<img width="261" alt="android2" src="https://github.com/IvanENERGY/AndroidStudio-Nodejs-Expressjs-MSSQLSERVER-demoCRUD/assets/90034836/819538cc-48ca-43be-9b3f-d1becee135a7"><br>
<span>[android3.png]</span><br>
<img width="259" alt="android3" src="https://github.com/IvanENERGY/AndroidStudio-Nodejs-Expressjs-MSSQLSERVER-demoCRUD/assets/90034836/df4b8d95-19bd-405e-b75e-5d370faf201e"><br>
<span>[android4.png]</span><br>
<img width="266" alt="android4" src="https://github.com/IvanENERGY/AndroidStudio-Nodejs-Expressjs-MSSQLSERVER-demoCRUD/assets/90034836/f182fa52-70fc-42e1-af51-c2a6128ccdfe"><br>

<span>[android4_2.png]</span><br>
<p>Newly created record is displayed on the list:</p>
<img width="256" alt="android4_2" src="https://github.com/IvanENERGY/AndroidStudio-Nodejs-Expressjs-MSSQLSERVER-demoCRUD/assets/90034836/550293bd-e3e0-47c2-b4bf-bea7ced9c04b"><br>

<h2>&#128308;UPDATE</h2>
<p>Press UPDATE button on the newly added record</p>
<span>[android5.png]</span><br><p>The original value of the record is displayed:</p>
<img width="263" alt="android5" src="https://github.com/IvanENERGY/AndroidStudio-Nodejs-Expressjs-MSSQLSERVER-demoCRUD/assets/90034836/c62e1d50-3c9e-4362-9286-8102b52a6a65"><br>
<span>[android6.png]</span><br><p>Make changes to taks name,deadline,reps and file path:</p>
<img width="266" alt="android6" src="https://github.com/IvanENERGY/AndroidStudio-Nodejs-Expressjs-MSSQLSERVER-demoCRUD/assets/90034836/52fb003d-9c6d-4979-88ea-b257352157ef"><br>
<span>[android7.png]</span><br>
<img width="257" alt="android7" src="https://github.com/IvanENERGY/AndroidStudio-Nodejs-Expressjs-MSSQLSERVER-demoCRUD/assets/90034836/accbc0f0-02aa-425d-be98-c7f116d172fb"><br>
<p>Updated result are displayed:</p>
<span>[android8.png]</span><br>
<img width="259" alt="android8" src="https://github.com/IvanENERGY/AndroidStudio-Nodejs-Expressjs-MSSQLSERVER-demoCRUD/assets/90034836/73b26b9f-337c-4eba-b594-8ad125ee865a"><br>

<h2>&#128308;DELETE</h2>
<span>[android9.png]</span><br>
<img width="260" alt="android9" src="https://github.com/IvanENERGY/AndroidStudio-Nodejs-Expressjs-MSSQLSERVER-demoCRUD/assets/90034836/9718df11-961b-4d4e-81b4-46199198f91b"><br>
<span>[android10.png]</span><br>
<img width="264" alt="android10" src="https://github.com/IvanENERGY/AndroidStudio-Nodejs-Expressjs-MSSQLSERVER-demoCRUD/assets/90034836/4f963b4b-7b6b-4155-9ff0-89b1c1d8ed3a"><br>
<h1>&#128221;Development Memos</h1>
<h2>&#x270f;Remember to configure AndroidManifest.xml for Internet Access </h2>
<pre>
&lt;uses-permission android:name="android.permission.INTERNET"/>  
&lt;application android:usesCleartextTraffic="true"> 
</pre>