package com.accession.io.common.contexts;

import com.accession.io.exception.AuthorizationException;
import com.accession.io.exception.reasons.AuthorizationReason;
import com.accession.io.message.models.UserModel;

/**
 * @author: Danial Eskandari
 * @createdAt: 2024-11-16 14:20:43
 */
public class UserContext {

    private static final ThreadLocal<UserModel> userIdThreadLocal = new ThreadLocal<>();

    public static void set(UserModel userModel) {
        userIdThreadLocal.set(userModel);
    }

    public static UserModel get() {
        UserModel userModel = userIdThreadLocal.get();
        if (userModel == null)
            throw new AuthorizationException(AuthorizationReason.userModelIsEmpty());
        return userModel;
    }
}
