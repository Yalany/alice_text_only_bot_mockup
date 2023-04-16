package game;

import protocol.AliceRequest;

public final class Request {
    private final AliceRequest request;

    public Request(final AliceRequest request) {
        assert request != null : "AliceRequest cannot be null";
        this.request = request;
    }

    AliceRequest getAliceRequest() {
        return request;
    }

    String getUserId() {
        return request.session.user.userId;
    }

    String[] getInput() {
        return request.request.nlu.tokens;
    }

    boolean isNewSession() {
        return request.session.isNew;
    }
}
