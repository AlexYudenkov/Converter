# Converter
Приложение для просмотра курса валют и конвертации.

![22](https://user-images.githubusercontent.com/57913130/133932661-f2685510-3f6a-47f1-a50d-4ed232cff741.jpg)
![11](https://user-images.githubusercontent.com/57913130/133932595-5619914e-8090-4d57-b38c-8b941f2daada.jpg)

Для обновления нужно просто потянуть вниз, использутся Swipe to refresh. 

Для предотвращения лишних запросов и угрозы быть забаненным на сервере от частых запросов используется кеширование, с временем жизни 3 минуты. 

При ошибке показывает экран ошибкок, при загрузке показывается лоадер:

![video_2021-09-19_21-42-55](https://user-images.githubusercontent.com/57913130/133932349-153999a4-a5ec-471b-9e23-b3601343dd1c.gif)

Используемые библиотеки:

  KOIN - библиотека для внедрения зависимостей, в отличие от Dagger более простой в использовании в маленьких проектах.
  
  Android Jetpack's Navigation - официально рекомендуемая библиотека для навигации
  
  RETROFIT2 - библиотека для соединения с сетью и загрузки данных, наиболее популярная и довольно простая в использовании.
  
  MOSHI - популярная библиотека для чтобы парсить JSON в Java объекты
  
  cache4k - простая библиотека для кеширования, поддерживает время жизни кеша

Полная демонстрация:
https://user-images.githubusercontent.com/57913130/133932722-e5fcae1f-acd4-4431-911e-cc7c8a5e4c8f.mp4
