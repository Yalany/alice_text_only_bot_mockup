package game;

import protocol.AliceResponse;

public final class Response {
  private final AliceResponse aliceResponse;

  Response(final Request input) {
    assert (input != null) : "Request cannot be null";
    var request = input.getAliceRequest();
    aliceResponse = new AliceResponse();
    aliceResponse.version = request.version;
  }

  Response setText(final String text) {
    aliceResponse.response.text = text;
    return this;
  }

  Response setTTS(final String tts) {
    aliceResponse.response.tts = tts;
    return this;
  }

  Response setButtons(final AliceResponse.Response.Button[] buttons) {
    aliceResponse.response.buttons = buttons;
    return this;
  }

  Response setEndSession(final boolean endSession) {
    aliceResponse.response.endSession = endSession;
    return this;
  }

  public AliceResponse toAliceResponse() {
    return aliceResponse;
  }
}
