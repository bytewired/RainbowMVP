package com.ne1c.rainbowmvp.processor;

import com.ne1c.rainbowmvp.annotaions.PresenterTag;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;

// Run for debug: ./gradlew --no-daemon -Dorg.gradle.debug=true :sample:clean :sample:compileDebugJavaWithJavac

public class PresenterTagProcessor extends AbstractProcessor {
    private Messager messager;
    private Filer filer;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);

        messager = processingEnvironment.getMessager();
        filer = processingEnvironment.getFiler();
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        for (Element e : roundEnv.getElementsAnnotatedWith(PresenterTag.class)) {
            if (e.getKind() != ElementKind.CLASS) {
                messager.printMessage(Diagnostic.Kind.ERROR, "Can be applied only to class.");
                return true;
            }

            String presenterTag = e.getAnnotation(PresenterTag.class).value();

            MethodSpec getPresenterTag = MethodSpec.methodBuilder("getPresenterTag")
                    .addModifiers(Modifier.PUBLIC)
                    .addAnnotation(Override.class)
                    .returns(String.class)
                    .addStatement("return $S", presenterTag)
                    .build();

            TypeSpec inheritsClass = TypeSpec.classBuilder(e.getSimpleName().toString() + "_PresenterTag")
                        .addModifiers(Modifier.PUBLIC)
                        .addMethod(getPresenterTag)
                        .superclass(ParameterizedTypeName.get(e.asType()))
                        .build();

            JavaFile testJavaFile = JavaFile.builder(e.getEnclosingElement().toString(), inheritsClass)
                    .build();

            try {
                testJavaFile.writeTo(filer);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        return true;
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> annotations = new LinkedHashSet<>();
        annotations.add(PresenterTag.class.getCanonicalName());
        return annotations;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }
}
