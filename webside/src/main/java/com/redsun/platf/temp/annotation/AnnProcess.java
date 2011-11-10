package com.redsun.platf.temp.annotation;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;

@SupportedSourceVersion(SourceVersion.RELEASE_6)
@SupportedAnnotationTypes("annotation.Assignment")
public class AnnProcess extends AbstractProcessor {
	private TypeElement assignmentElement;

	public synchronized void init(ProcessingEnvironment processingEnv) {
		super.init(processingEnv);
		Elements elementUtils = processingEnv.getElementUtils();
		assignmentElement = elementUtils
				.getTypeElement("annotation.Assignment");
	}

	@Override
	public boolean process(Set<? extends TypeElement> annotations,
			RoundEnvironment roundEnv) {
		Set<? extends Element> elements = roundEnv
				.getElementsAnnotatedWith(assignmentElement);
		for (Element element : elements) {
			processAssignment(element);
		}
		return true;
	}

	private void processAssignment(Element element) {
		List<? extends AnnotationMirror> annotations = element
				.getAnnotationMirrors();
		for (AnnotationMirror mirror : annotations) {
			if (mirror.getAnnotationType().asElement()
					.equals(assignmentElement)) {
				Map<? extends ExecutableElement, ? extends AnnotationValue> values = mirror
						.getElementValues();
				
				String assignee = (String) getAnnotationValue(values,"assignee"); // 获取注解的值
			
			}
		}
	}

	private String getAnnotationValue(
			Map<? extends ExecutableElement, ? extends AnnotationValue> values,
			String string) {
		// TODO Auto-generated method stub
		
		// print out the goodies.
//		SourcePosition position = mirror.getPosition();
//		Map<AnnotationTypeElementDeclaration, AnnotationValue> values = mirror
//				.getElementValues();
//
//		System.out.println("Declaration: " + declaration.toString());
//		System.out.println("Position: " + position);
//		System.out.println("Values:");
//		for (Map.Entry<AnnotationTypeElementDeclaration, AnnotationValue> entry : values
//				.entrySet()) {
//			AnnotationTypeElementDeclaration elemDecl = entry.getKey();
//			AnnotationValue value = entry.getValue();
//			System.out.println("    " + elemDecl + "=" + value);
//		}
		return null;
	}

	// @Override
	// public boolean process(Set<? extends TypeElement> annotations,
	// RoundEnvironment roundEnv) {
	// // TODO Auto-generated method stub
	// return false;
	//		
	// }

}
