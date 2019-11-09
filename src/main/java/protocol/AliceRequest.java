package protocol;

import com.google.gson.annotations.SerializedName;

/**
 *  Запрос, полученный на хук от пользователя Алисы.
 *  Содержит в себе все данные запроса, как пользовательские, так и системные.
 */
public final class AliceRequest {
    /**
     *  Информация об устройстве, с помощью которого пользователь разговаривает с Алисой.
     */
    @SerializedName("meta")
    public Meta meta;

    /**
     *  Данные, полученные от пользователя.
     */
    @SerializedName("request")
    public Request request;

    /**
     *  Данные о сессии.
     */
    @SerializedName("session")
    public Session session;

    /**
     *  Версия протокола. Текущая версия — 1.0.
     */
    @SerializedName("version")
    public String version;


    public final class Meta {
        /**
         *  Язык в POSIX-формате, максимум 64 символа.
         */
        @SerializedName("locale")
        public String locale;

        /**
         *  Название часового пояса, включая алиасы, максимум 64 символа.
         */
        @SerializedName("timezone")
        public String timezone;

        /**
         *  Идентификатор устройства и приложения, в котором идет разговор, максимум 1024 символа.
         */
        @Deprecated
        @SerializedName("client_id")
        public String clientId;

        /**
         *  Интерфейсы, доступные на устройстве пользователя.
         */
        @SerializedName("interfaces")
        public Interfaces interfaces;


        public final class Interfaces {
            /**
             *  Пользователь может видеть ответ навыка на экране и открывать ссылки в браузере.
             */
            @SerializedName("screen")
            public Object screen = null;
        }
    }

    public final class Request {
        /**
         *  Служебное поле: запрос пользователя, преобразованный для внутренней обработки Алисы.
         *  В ходе преобразования текст, в частности, очищается от знаков препинания, а числительные преобразуются в числа.
         */
        @SerializedName("command")
        public String command;

        /**
         *  Полный текст пользовательского запроса, максимум 1024 символа.
         */
        @SerializedName("original_utterance")
        public String originalUtterance;

        /**
         *  Тип ввода, обязательное свойство. Возможные значения:
         *      "SimpleUtterance" — голосовой ввод;
         *      "ButtonPressed" — нажатие кнопки.
         */
        @SerializedName("type")
        public String type;

        /**
         *  JSON, полученный с нажатой кнопкой от обработчика навыка (в ответе на предыдущий запрос), максимум 4096 байт.
         */
        @SerializedName("payload")
        public Payload payload;

        /**
         *  Слова и именованные сущности, которые Диалоги извлекли из запроса пользователя.
         */
        @SerializedName("nlu")
        public Nlu nlu;



        public final class Nlu {
            /**
             *  Массив слов из произнесенной пользователем фразы.
             */
            @SerializedName("tokens")
            public String[] tokens;

            /**
             *  Массив именованных сущностей.
             */
            @SerializedName("entities")
            public Entity[] entities;


            public final class Entity {
                /**
                 *  Тип именованной сущности. Возможные значения:
                 *
                 *      YANDEX.GEO — местоположение (адрес или аэропорт).
                 *          "country": "россия",
                 *          "city": "москва",
                 *          "street": "улица льва толстого",
                 *          "house_number": "16"
                 *
                 *      YANDEX.FIO — фамилия, имя и отчество.
                 *          "first_name": "антон",
                 *          "patronymic_name": "павлович",
                 *          "last_name": "чехов"
                 *
                 *      YANDEX.NUMBER — число, целое или с плавающей точкой.
                 *          "value": 16.2
                 *
                 *      YANDEX.DATETIME — дата и время, абсолютные или относительные.
                 *          "year": 1982,
                 *          "month": 9,
                 *          "day": 15,
                 *          "hour": 22,
                 *          "minute": 30
                 */
                @SerializedName("type")
                public String type;

                /**
                 *  Формальное описание именованной сущности.
                 */
                @SerializedName("value")
                public Object value;

                /**
                 *  Обозначение начала и конца именованной сущности в массиве слов. Нумерация слов в массиве начинается с 0.
                 */
                @SerializedName("tokens")
                public Tokens tokens;


                public final class Tokens {
                    /**
                     *  Первое слово именованной сущности.
                     */
                    @SerializedName("start")
                    public int start;

                    /**
                     *  Первое слово после именованной сущности.
                     */
                    @SerializedName("end")
                    public int end;
                }
            }
        }
    }

    public final class Session {
        /**
         *  Признак новой сессии. Возможные значения:
         *      true — пользователь начинает новый разговор с навыком;
         *      false — запрос отправлен в рамках уже начатого разговора.
         */
        @SerializedName("new")
        public boolean isNew;

        /**
         *  Идентификатор сообщения в рамках сессии, максимум 8 символов.
         *  Инкрементируется с каждым следующим запросом.
         */
        @SerializedName("message_id")
        public int messageId;

        /**
         *  Уникальный идентификатор сессии, максимум 64 символов.
         */
        @SerializedName("session_id")
        public String sessionId;

        /**
         *  Идентификатор вызываемого навыка, присвоенный при создании.
         */
        @SerializedName("skill_id")
        public String skillId;

        /**
         * Идентификатор экземпляра приложения, в котором пользователь общается с Алисой, максимум 64 символа.
         * Даже если пользователь авторизован с одним и тем же аккаунтом в приложении Яндекс для Android и iOS,
         * Яндекс.Диалоги присвоят отдельный user_id каждому из этих приложений.
         */
        @SerializedName("user_id")
        public String userId;
    }
}
