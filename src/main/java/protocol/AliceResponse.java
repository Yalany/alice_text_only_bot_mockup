package protocol;

import com.google.gson.annotations.SerializedName;

/**
 *  Ответ, сформированный на конкретный запрос.
 */
@SuppressWarnings("unused")
public final class AliceResponse {
  public AliceResponse() {
    response = new Response();
  }

  /**
   *  Данные для ответа пользователю.
   */
  @SerializedName("response")
  public Response response;

  /**
   *  Версия протокола. Текущая версия — 1.0.
   */
  @SerializedName("version")
  public String version;

  /**
   *  Помимо описанного функционала возможна отправка изображений.
   *  Подробнее: https://yandex.ru/dev/dialogs/alice/doc/protocol-docpage/#response
   */
  public static final class Response {
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

    public static final class Button {
      /**
       *  Текст кнопки, обязателен для каждой кнопки. Максимум 64 символа.
       *  Если для кнопки не указано свойство url, по нажатию текст кнопки будет отправлен навыку как реплика пользователя.
       */
      @SerializedName("title")
      public String title;

      /**
       *  Признак того, что кнопку нужно убрать после следующей реплики пользователя. Допустимые значения:
       *      false — кнопка должна оставаться активной (значение по умолчанию);
       *      true — кнопку нужно скрывать после нажатия.
       */
      @SerializedName("hide")
      public boolean hide;
    }
  }
}
