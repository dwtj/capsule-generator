package capsule.check.nullness;

import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;

@SupportedSourceVersion(SourceVersion.RELEASE_8)
@SupportedAnnotationTypes({"capsule.check.nullness.NonNull", "capsule.check.nullness.IsNull"})
public class NullnessProcessor extends AbstractProcessor {
	
	public NullnessProcessor(ProcessingEnvironment processingEnv) {
		super();
	}

	@Override
	public boolean process(Set<? extends TypeElement> annotations,
			RoundEnvironment roundEnv) {
		
		for (Element elem : roundEnv.getElementsAnnotatedWith(IsNull.class)) {
			IsNull isNull = elem.getAnnotation(IsNull.class);
			handleIsNull(isNull);
		}
		
		for (Element elem : roundEnv.getElementsAnnotatedWith(NonNull.class)) {
			NonNull nonNull = elem.getAnnotation(NonNull.class);
			handleNonNull(nonNull);
		}
		
		return false;
	}
	
	private boolean handleNonNull(NonNull elem) {
		return !elem.equals(null);
	}
	
	private boolean handleIsNull(IsNull elem) {
		return elem.equals(null);
	}
}
