# Угадай аккорд

### Описание

Игра "Угадай аккорд" позволяет проверить свои знания форм аккордов. Поддерживается три вида инструментов: гитара, пианино и укулеле.

В игре вы можете выбрать уровень сложности и количество раундов, тем самым, можно начать с небольшой нагрузки, а с опытом выбирать всё более сложные настройки.

"Угадай аккорд" это уникальный инструмент для тренировок по теории музыки.

### Система сборки

Версии зависимостей вынесены в настройки gradle.properties:
```
# Versions
core_version=1.10.1
appcompat_version=1.6.1
material_version=1.9.0
constraint_version=2.1.4
binding_version=1.5.9
navigation_version=2.5.3
```

Список зависимостей:
```
dependencies {
    // Android
    implementation "androidx.core:core-ktx:$core_version"

    // Design
    implementation "androidx.appcompat:appcompat:$appcompat_version"
    implementation "com.google.android.material:material:$material_version"
    implementation "androidx.constraintlayout:constraintlayout:$constraint_version"

    // ViewBinding
    implementation "com.github.kirich1409:viewbindingpropertydelegate-full:$binding_version"

    // Navigation
    implementation "androidx.navigation:navigation-fragment-ktx:$navigation_version"
    implementation "androidx.navigation:navigation-ui-ktx:$navigation_version"
}
```
