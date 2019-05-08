package com.utilities;

public class VaporcitoUtils {
    public static String[] tokenizeMsg(String msg) {
        return msg.split("\\s+");
    }

    public static boolean isMatch(String reference, String msg, int targetDistance) {
        String[] tokenizedMsg = tokenizeMsg(msg);
        String[] tokenizedReference = tokenizeMsg(reference);

        if (tokenizedReference.length > 2)
            throw new IllegalArgumentException("The reference MUST HAVE 1 or 2 words only.");

        if (tokenizedReference.length > 1) {
            for (int i = 0; i < tokenizedMsg.length; i++) {
                if (DamerauLevenshtein.calculateDistance(tokenizedMsg[i], tokenizedReference[0]) <= targetDistance) {
                    if ((i + 1) < tokenizedMsg.length) {
                        if (DamerauLevenshtein.calculateDistance(tokenizedMsg[i + 1], tokenizedReference[1]) <= targetDistance)
                            return true;
                    }
                }
            }
        }

        for (String token:
             tokenizedMsg) {
            if (DamerauLevenshtein.calculateDistance(token, reference) <= targetDistance)
                return true;
        }

        return false;
    }

    public static boolean isMatchNoCaseSensitive(String reference, String msg, int targetDistance) {
        return isMatch(reference.toLowerCase(), msg.toLowerCase(), targetDistance);
    }
}
