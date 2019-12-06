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
        return request.session.userId;
    }

    String[] getInput() {
        return request.request.nlu.tokens;
    }

    boolean isNewSession() {
        return request.session.isNew;
    }
}




/*
String getLocale() {
    return request.meta.locale;
}

String getTimezone() {
    return request.meta.timezone;
}

String getClientId() {
    return request.meta.clientId;
}

boolean haveScreen() {
    return request.meta.interfaces.screen != null;
}

boolean isVoiceInput() {
    return request.request.type.equals("SimpleUtterance");
}

boolean isButtonInput() {
    return request.request.type.equals("ButtonPressed");
}

String getSkillId() {
    return request.session.skillId;
}
*/
