package jp.pinkikki.advice;

import jp.pinkikki.domain.hero.model.SceneName;
import jp.pinkikki.editor.SceneNamePropertyEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

@ControllerAdvice
public class PropertyEditorControllerAdvice {

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        // Enumの変換はConversionServiceで実施するから、不要
//        binder.registerCustomEditor(SceneName.class, new SceneNamePropertyEditor());
        binder.registerCustomEditor(SceneName.class, new StringTrimmerEditor(true));
    }
}
