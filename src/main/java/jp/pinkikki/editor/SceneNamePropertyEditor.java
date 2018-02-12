package jp.pinkikki.editor;

import jp.pinkikki.domain.hero.model.SceneName;

import java.beans.PropertyEditorSupport;

public class SceneNamePropertyEditor extends PropertyEditorSupport {

    @Override
    public void setAsText(String text) {
        setValue(SceneName.valueOf(text));
    }
}
