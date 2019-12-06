package game;

import protocol.AliceResponse;

public final class Response {
    private final AliceResponse response;

    Response(final Request input) {
        assert (input != null) : "Request cannot be null";
        var request = input.getAliceRequest();
        response = new AliceResponse();
        response.session.messageId = request.session.messageId;
        response.session.sessionId = request.session.sessionId;
        response.session.userId = request.session.userId;
        response.version = request.version;
    }

    Response setText(final String text) {
        response.response.text = text;
        return this;
    }

    Response setTTS(final String tts) {
        response.response.tts = tts;
        return this;
    }

    Response setButtons(final AliceResponse.Response.Button[] buttons) {
        response.response.buttons = buttons;
        return this;
    }

    Response setEndSession(final boolean endSession) {
        response.response.endSession = endSession;
        return this;
    }

    public AliceResponse toAliceResponse() {
        return response;
    }
}
