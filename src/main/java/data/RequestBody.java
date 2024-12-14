package data;

import model.b2b_user.B2bUserElement;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.joda.time.LocalDateTime;

public class RequestBody {
    // Тело запроса для создания пользователя
    public static B2bUserElement getB2bUserCreateRequestBody() {
        return new B2bUserElement(
                1,
                "test_" + RandomStringUtils.random(25, true, true),
                "test_" + RandomStringUtils.random(25, true, true),
                "test_" + RandomStringUtils.random(25, true, true),
                RandomStringUtils.random(25, false, true),
                "mail" + RandomStringUtils.random(50, true, true) + "@mail.ru",
                "test_" + RandomStringUtils.random(25, true, true),
                RandomUtils.nextInt(10000, 99999),
                RandomUtils.nextInt(100, 999),
                0.0,
                "test_" + RandomStringUtils.random(25, true, true),
                RandomStringUtils.random(25, true, true),
                true,
                RandomStringUtils.random(25, true, true),
                "test_" + RandomStringUtils.random(25, true, true),
                "test_" + RandomStringUtils.random(10, true, true),
                "test_" + RandomStringUtils.random(25, true, true),
                "OFFER",
                "NEW",
                "+" + RandomStringUtils.random(25, false, true),
                true,
                true,
                "+79999999999",
                "test_" + RandomStringUtils.random(50, true, true) + "@mail.ru",
                null,
                null,
                null,
                null
        );
    }

    // Тело запроса для создания пользователя
    public static B2bUserElement getB2bUserUpdateRequestBody() {
        return new B2bUserElement(
                "test_" + RandomStringUtils.random(30, true, true),
                "test_" + RandomStringUtils.random(30, true, true),
                "test_" + RandomStringUtils.random(30, true, true),
                RandomStringUtils.random(30, false, true),
                "mail" + RandomStringUtils.random(50, true, true) + "@mail.ru",
                RandomStringUtils.random(30, true, true),
                RandomUtils.nextInt(10000, 99999),
                RandomUtils.nextInt(10000, 99999),
                RandomUtils.nextInt(100, 999),
                3.0,
                "test_" + RandomStringUtils.random(30, true, true),
                RandomStringUtils.random(30, true, true),
                false,
                "test_" + RandomStringUtils.random(30, true, true),
                "test_" + RandomStringUtils.random(30, true, true),
                "test_" + RandomStringUtils.random(20, true, true),
                "test_" + RandomStringUtils.random(30, true, true),
                "OTHER",
                "APPROVED",
                "ADMIN",
                "+" + RandomStringUtils.random(25, false, true),
                true,
                true,
                "+79999999990",
                "test2_" + RandomStringUtils.random(50, true, true) + "@mail.ru",
                null,
                LocalDateTime.now().toString() + "Z",
                LocalDateTime.now().toString() + "Z",
                LocalDateTime.now().toString() + "Z"
        );
    }
}
