package com.ne1c.rainbowmvp.annotaions;

import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;

@SupportedAnnotationTypes({"com.ne1c.rainbowmvp.annotaions.StorableView"})
@SupportedSourceVersion(SourceVersion.RELEASE_6)
public class StorableProcessor extends AbstractProcessor {
    public StorableProcessor() {}

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment env) {
        for (Element e : env.getElementsAnnotatedWith(StorableView.class)) {
            if (e.getKind() == ElementKind.INTERFACE) {
            }
        }
        return false;
    }
}
