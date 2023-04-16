package protocol;

import com.google.gson.annotations.SerializedName;

/**
 *  Запрос, полученный на хук от пользователя Алисы.
 *  Содержит в себе все данные запроса, как пользовательские, так и системные.
 */
@SuppressWarnings("unused")
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
     *  Данные о сессии. Сессия — это период относительно непрерывного взаимодействия пользователя с навыком.
     *  Сессия завершается, когда:
     *     пользователь запрашивает выход из навыка;
     *     навык явно завершает работу ("end_session": true);
     *     от пользователя долго не поступает команд (тайм-аут зависит от поверхности, минимум несколько минут).
     */
    @SerializedName("session")
    public Session session;

    /**
     *  Версия протокола. Текущая версия — 1.0.
     */
    @SerializedName("version")
    public String version;

    public static final class Meta {
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

 static final class Interfaces {
            /**
             *  Пользователь может видеть ответ навыка на экране и открывать ссылки в браузере.
             */
            @SerializedName("screen")
            public Object screen = null;

            /**
             *  У пользователя есть возможность запросить связку аккаунтов.
             */
            @SerializedName("account_linking")
            public Object accountLinking = null;

            /**
             *  На устройстве пользователя есть аудиоплеер.
             */
            @SerializedName("audio_player")
            public Object audioPlayer = null;
        }
    }

    public static final class Request {
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
         *  Формальные характеристики реплики, которые удалось выделить Яндекс Диалогам. Свойство отсутствует, если ни одно из вложенных свойств не применимо.
         */
        @SerializedName("markup")
        public Markup markup;

        /**
         *  Слова и именованные сущности, которые Диалоги извлекли из запроса пользователя.
         */
        @SerializedName("nlu")
        public Nlu nlu;

        /**
         *  Тип ввода, обязательное свойство. Возможные значения:
         *      "SimpleUtterance" — голосовой ввод.
         */
        @SerializedName("type")
        public String type;

        public static final class Markup {
            /**
             * Признак реплики, которая содержит криминальный подтекст (самоубийство, разжигание ненависти, угрозы). Вы можете настроить навык на определенную реакцию для таких случаев — например, отвечать «Не понимаю, о чем вы. Пожалуйста, переформулируйте вопрос.»
             *
             * Возможно только значение true. Если признак не применим, это свойство не включается в ответ.
             */
            @SerializedName("dangerous_context")
            boolean dangerous_context;
        }

        public static final class Nlu {
            /**
             *  Массив слов из произнесенной пользователем фразы.
             */
            @SerializedName("tokens")
            public String[] tokens;
        }
    }

    public static final class Session {
        /**
         *  Уникальный идентификатор сессии, максимум 64 символов.
         */
        @SerializedName("session_id")
        public String sessionId;

        /**
         *  Идентификатор сообщения в рамках сессии, максимум 8 символов.
         *  Инкрементируется с каждым следующим запросом.
         */
        @SerializedName("message_id")
        public int messageId;

        /**
         *  Идентификатор вызываемого навыка, присвоенный при создании.
         */
        @SerializedName("skill_id")
        public String skillId;

        /**
         *  Атрибуты пользователя Яндекса, который взаимодействует с навыком. Если пользователь не авторизован в приложении, свойства user в запросе не будет.
         */
        @SerializedName("user")
        public User user;

        /**
         *  Признак новой сессии. Возможные значения:
         *      true — пользователь начинает новый разговор с навыком;
         *      false — запрос отправлен в рамках уже начатого разговора.
         */
        @SerializedName("new")
        public boolean isNew;

        public static final class User {
            /**
             * Идентификатор пользователя Яндекса, единый для всех приложений и устройств.
             * Этот идентификатор уникален для пары «пользователь — навык»: в разных навыках значение свойства user_id для одного и того же пользователя будет различаться.
             */
            public String userId;

            /**
             *  Токен для OAuth-авторизации, который также передается в заголовке Authorization для навыков с настроенной связкой аккаунтов.
             */
            public String accessToken;
        }
    }
}
