# Рефакторинг баз данных и приложений
Репозиторий по курсу "Рефакторинг баз данных и приложений", Университет ИТМО

### Студенты:
Сущенко Роман P34131

Бусыгин Дмитрий P34131

# Gadget depo

---

**Описание проекта**  
Проект представляет собой готовое программное решение из двух компонентов для поиска и приобретения техники.
Администраторы могут добавлять новые позиции товара, выставляя им характеристики и цену, пользователи могут искать эти товары и покупать.
Оплата покупки будет осуществляться через запросы в отдельный сервис, имитирующий работу систему банковского биллинга

**Сценарии использования**
1. Регистрация и аутентификация пользователей
2. Создание, удаление, редактирование карточки товара
3. Поиск товара по категории или названию
4. Покупка товара

**Компоненты системы**
* Backend-приложение на Kotlin и Spring Framework, отвечающее за работу с карточкой товара (gadget_service)
  * Оперирует базой данных PostgreSQL 
* Backend-приложение на Kotlin и Spring Framework, отвечающее за процесс оплаты товара при покупке (billing_service)
  * Оперирует базой данных PostgreSQL

---

# Этапы разработки проекта

### Этап 1: Планирование

На данном этапе фокусируемся на сборе и анализе требований, проектировании архитектуры системы и подготовке плана разработки.

План:
1. Изучить сценарии использования, чтобы понять все необходимые функции и их взаимосвязи.
2. Определить, как компоненты системы будут взаимодействовать друг с другом. Спроектировать REST API, описывающее основные взаимодействия.
3. Подготовить окружение для разработки, выбрать необходимые библиотеки для Spring. Настроить PostgreSQL и средство осуществления
миграций БД для использования в системе.

### Этап 2: Разработка MVP 

Создать минимальную рабочую версию системы, которая будет включать основные функции для проверки концепции. Сбор первоначальной обратной связи.

План:
1. Реализовать регистрацию и аутентификацию пользователей.
2. Создать модуль для работы с карточками товаров.
3. Создать модуль для оплаты покупок.
4. Протестировать и отладить работу MVP. Провести тестирование всех функций и устраните обнаруженные баги.

### Этап 3: Улучшение и расширение MVP

На этом этапе улучшаем платформу на основании обратной связи пользователей и собранных данных.

План:
1. Расширить функциональность. Добавить следующие фичи:
   - История покупок
   - Список избранного
2. Повысить уровень безопасности посредством внедрения двухфакторной аутентификации.
3. Оптимизировать производительность системы. Внедрить кеширование.

------

## Общая информация о процессе и технологиях:
1. Контроль версий и совместная разработка будет вестись через текущий git-репозиторий
2. Миграции БД будем осуществлять с использованием Flyway
3. Реализация UI в рамках выполнения проекта не предусмотрена, демо-записи и финальная демонстрация будет проводиться через платформу тестирования API (Bruno/Postman)
