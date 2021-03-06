package be.kwakeroni.evelyn.test;

import be.kwakeroni.evelyn.model.ParseException;
import org.assertj.core.api.ThrowableAssert;

public class Assertions extends org.assertj.core.api.Assertions {

    public static ParseExceptionAssert assertThat(ParseException parseException) {
        return new ParseExceptionAssert(parseException);
    }

    public static ParseExceptionAssert assertThatParseExceptionThrownBy(ThrowableAssert.ThrowingCallable shouldRaiseThrowable) {
        return ParseExceptionAssert.assertThatThrownBy(shouldRaiseThrowable);
    }

}
