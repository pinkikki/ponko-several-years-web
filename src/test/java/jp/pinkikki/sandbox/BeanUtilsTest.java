package jp.pinkikki.sandbox;

import lombok.Data;
import org.junit.Test;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;
import java.util.stream.IntStream;

public class BeanUtilsTest {

    @Test
    public void test1() {
        Form form2 = getFormWithDefaultValues();

        Form form = new Form();
        BeanUtils.copyProperties(form2, form);
        IntStream.rangeClosed(1, 4).forEach(i -> {
            try {
                System.out.println(BeanUtils.getPropertyDescriptor(Form.class, "item" + i)
                        .getReadMethod().invoke(form));
            } catch (Throwable e) {
                e.printStackTrace();
            }
        });
    }

    private Form getFormWithDefaultValues() {
        Form form = new Form();
        form.setItem1("string");
        form.setItem2(2);
        form.setItem3(LocalDateTime.now());
        form.setItem4(true);
        return form;
    }

    @Data
    class Form {
        String item1;
        Integer item2;
        LocalDateTime item3;
        Boolean item4;
    }

}
