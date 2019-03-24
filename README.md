# PushService
1. Интеграция Firebase
	Подробно процесс описан здесь https://firebase.google.com/docs/android/setup?authuser=0
2. добавить библиотеку PushService в проект.
	- скопировать pushservice-release.aar в директорию /APP_NAME/libs/
	- добавить зависимость в build.gradle
		dependencies {
	    	implementation fileTree(dir: 'libs', include: ['*.aar'])
	    	implementation "org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.3.20"
		}
	- добавить файл ZGRConfig.json в директорию assets проекта
3. Подписка на пуши:
    - При старте приложения требуется вызвать:
    	      NotificationSdk.getInstance(context)
                .registerPushToken({
                    //Successful register token
                }, {
                    //Error while register token
                })
  - Для отладки рекомендуется использовать флаг NotificationSdk.getInstance(context).setLogsEnabled(true), но не забыть выключить его при релизной сборке.
  - По умолчанию при показе нотификации отображается иконка приложения. Ее можно изменить вызвав метод
            NotificationSdk.getInstance(context).setNotificationIconResId(R.drawable.notification_icon)

4. Для регистрация номера телефона требуется вызмать метод:
	NotificationSdk.getInstance(this).registerPhoneNumber(
                "89151028379", {
                //Successful register phone number
            }, {
                //Error while register phone number
            })

5. Все методы SDK необходимо вызывать из main thread(https://developer.android.com/guide/components/processes-and-threads?hl=ru#Threads)
