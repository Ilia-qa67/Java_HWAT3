import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.cssClass;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


class GetCardTest {

    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
    }

    @Test
    void shouldTest() {
        $("[data-test-id=name] input").setValue("Екатерина Ворошилова");
        $("[data-test-id=phone] input").setValue("+79048631049");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id=order-success]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void shouldNotTestName() {
        $("[data-test-id=name] input").setValue("Ekaterina");
        $("[data-test-id=phone] input").setValue("+79048631049");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id=name].input_invalid .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldNotTestNameNull() {
        $("[data-test-id=name] input").setValue("");
        $("[data-test-id=phone] input").setValue("+79048631049");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id=name].input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    void shouldNotTestPhone() {
        $("[data-test-id=name] input").setValue("Екатерина Ворошилова");
        $("[data-test-id=phone] input").setValue("+7904863104");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id=phone].input_invalid .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldNotTestPhoneNull() {
        $("[data-test-id=name] input").setValue("Екатерина Ворошилова");
        $("[data-test-id=phone] input").setValue("");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id=phone].input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    void shouldNotTestAgreement() {
        $("[data-test-id=name] input").setValue("Екатерина Ворошилова");
        $("[data-test-id=phone] input").setValue("+79048631049");
        $("button").click();
        $("[data-test-id=agreement]").shouldHave(cssClass("input_invalid"));
    }
}

