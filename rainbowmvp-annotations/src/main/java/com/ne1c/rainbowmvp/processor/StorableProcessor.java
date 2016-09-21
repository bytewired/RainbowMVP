package com.ne1c.rainbowmvp.processor;

import com.google.auto.service.AutoService;
import com.ne1c.rainbowmvp.annotaions.StorableView;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;

@AutoService(Processor.class)
public class StorableProcessor extends AbstractProcessor {
    public StorableProcessor() {
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment env) {
        for (Element e : env.getElementsAnnotatedWith(StorableView.class)) {
            if (e.getKind() == ElementKind.INTERFACE) {
            }
        }
        return false;
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> annotations = new LinkedHashSet<>();
        annotations.add(StorableView.class.getCanonicalName());
        return annotations;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }
}
