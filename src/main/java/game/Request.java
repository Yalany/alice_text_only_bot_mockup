package game;

import protocol.AliceRequest;

public final class Request {
  private final AliceRequest aliceRequest;

  public Request(final AliceRequest aliceRequest) {
    assert aliceRequest != null : "AliceRequest cannot be null";
    this.aliceRequest = aliceRequest;
  }

  AliceRequest getAliceRequest() {
    return aliceRequest;
  }

  String getUserId() {
    return aliceRequest.session.user.userId;
  }

  String[] getInput() {
    return aliceRequest.request.nlu.tokens;
  }

  boolean isNewSession() {
    return aliceRequest.session.isNew;
  }
}
