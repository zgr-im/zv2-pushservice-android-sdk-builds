# PushService

1. Интегрируйте ваше приложение с сервисом Google Firebase, если интеграция ещё не была осуществлена.

Подробно процесс описан здесь https://firebase.google.com/docs/android/setup

2. Добавьте библиотеку PushService в ваш проект.

- Скопируйте aar файл в директорию `/APP_NAME/libs/`.
- Добавьте зависимость в `build.gradle`:

```
dependencies {
    implementation fileTree(dir: 'libs', include: ['*.aar'])
    implementation "org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.3.20"
}
```

- Добавьте файл `ZGRConfig.json` в директорию `assets` проекта.

3. Подпишитесь на PUSH уведомления.

- При старте приложения требуется вызвать:

```
NotificationSdk.getInstance(context)
    .registerPushToken({
        //Successful register token
    }, {
        //Error while register token
    });
```
                
- Для отладки рекомендуется использовать флаг `NotificationSdk.getInstance(context).setLogsEnabled(true)` (не забыть выключить его в релизной сборке).

- По умолчанию при показе нотификации отображается иконка приложения. Ее можно изменить вызвав метод `NotificationSdk.getInstance(context).setNotificationIconResId(R.drawable.notification_icon)`.

4. Для регистрация номера телефона требуется вызмать метод:

```
NotificationSdk.getInstance(this).registerPhoneNumber(
    "89151028379", {
        //Successful register phone number
    }, {
        //Error while register phone number
    });
```

5. Все методы SDK необходимо вызывать из [`main thread`](https://developer.android.com/guide/components/processes-and-threads?hl=ru#Threads).
