package protocol;

import com.google.gson.annotations.SerializedName;

/**
 *  Ответ, сформированный на конкретный запрос.
 */
public final class AliceResponse {
    /**
     *  Данные для ответа пользователю.
     */
    @SerializedName("response")
    public Response response;

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


    /**
     *  Помимо описанного функционала возможна отправка изображений.
     *  Подробнее: https://yandex.ru/dev/dialogs/alice/doc/protocol-docpage/#response
     */
    public final class Response {
        /**
         *  Текст, который следует показать и сказать пользователю. Максимум 1024 символа. Не должен быть пустым.
         *  Текст также используется, если у Алисы не получилось отобразить включенную в ответ карточку (response.card).
         *  На устройствах, которые поддерживают только голосовое общение с навыком, это будет происходить каждый раз,
         *  когда навык присылает карточку в ответе.
         *  В тексте ответа можно указать переводы строк последовательностью «\n», например:
         *      "Отдых напрасен. Дорога крута.\nВечер прекрасен. Стучу в ворота."
         */
        @SerializedName("text")
        public String text;

        /**
         *  Ответ в формате TTS (text-to-speech), максимум 1024 символа.
         *  Docs:
         *      https://yandex.ru/dev/dialogs/alice/doc/speech-tuning-docpage/
         *      https://yandex.ru/dev/dialogs/alice/doc/sounds-docpage/
         *      https://yandex.ru/dev/dialogs/alice/doc/resource-sounds-upload-docpage/
         */
        @SerializedName("tts")
        public String tts;

        /**
         *  Кнопки, которые следует показать пользователю.
         *  Все указанные кнопки выводятся после основного ответа Алисы, описанного в свойствах  response.text и response.card.
         *  Кнопки можно использовать как релевантные ответу ссылки или подсказки для продолжения разговора.
         */
        @SerializedName("buttons")
        public Button[] buttons;

        /**
         *  Признак конца разговора.
         *  Допустимые значения:
         *      false — сессию следует продолжить;
         *      true — сессию следует завершить.
         */
        @SerializedName("end_session")
        public boolean endSession;


        public final class Button {
            /**
             *  Текст кнопки, обязателен для каждой кнопки. Максимум 64 символа.
             *  Если для кнопки не указано свойство url, по нажатию текст кнопки будет отправлен навыку как реплика пользователя.
             */
            @SerializedName("title")
            public String title;

            /**
             *  Произвольный JSON, который Яндекс.Диалоги должны отправить обработчику, если данная кнопка будет нажата. Максимум 4096 байт.
             */
            @SerializedName("payload")
            public Payload payload;

            /**
             *  URL, который должна открывать кнопка, максимум 1024 байта.
             *  Если свойство url не указано, по нажатию кнопки навыку будет отправлен текст кнопки.
             */
            @SerializedName("url")
            public String url;

            /**
             *  Признак того, что кнопку нужно убрать после следующей реплики пользователя. Допустимые значения:
             *      false — кнопка должна оставаться активной (значение по умолчанию);
             *      true — кнопку нужно скрывать после нажатия.
             */
            @SerializedName("hide")
            public boolean hide;
        }
    }

    public final class Session {
        /**
         * Идентификатор экземпляра приложения, в котором пользователь общается с Алисой, максимум 64 символа.
         * Даже если пользователь авторизован с одним и тем же аккаунтом в приложении Яндекс для Android и iOS,
         * Яндекс.Диалоги присвоят отдельный user_id каждому из этих приложений.
         */
        @SerializedName("user_id")
        public String userId;

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
    }
}
