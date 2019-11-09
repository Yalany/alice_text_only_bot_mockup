package game;

import com.google.gson.annotations.SerializedName;
import protocol.Payload;

public final class Button {

    /**
     * Текст кнопки, обязателен для каждой кнопки. Максимум 64 символа.
     * Если для кнопки не указано свойство url, по нажатию текст кнопки будет отправлен навыку как реплика пользователя.
     */
    @SerializedName("title")
    private String title;

    /**
     * URL, который должна открывать кнопка, максимум 1024 байта.
     * Если свойство url не указано, по нажатию кнопки навыку будет отправлен текст кнопки.
     */
    @SerializedName("url")
    private String url;

    /**
     * Произвольный JSON, который Яндекс.Диалоги должны отправить обработчику, если данная кнопка будет нажата.
     * Максимум 4096 байт.
     */
    @SerializedName("payload")
    private Payload payload;

    /**
     *  Признак того, что кнопку нужно убрать после следующей реплики пользователя. Допустимые значения:
     *      true — кнопка должна оставаться активной (значение по умолчанию);
     *      false — кнопку нужно скрывать после нажатия.
     */
    @SerializedName("hide")
    private boolean hide;

    public Button() {
        title = "dialog_button";
        payload = null;
        url = "example.com";
        hide = true;
    }

    public Button setTitle(final String title) {
        this.title = title;
        return this;
    }

    public Button setPayload(final Payload payload) {
        this.payload = payload;
        return this;
    }

    public Button setUrl(final String url) {
        this.url = url;
        return this;
    }

    public Button setHide(final boolean hide) {
        this.hide = hide;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Payload getPayload() {
        return payload;
    }

    public String getUrl() {
        return url;
    }

    public boolean isHide() {
        return hide;
    }
}
