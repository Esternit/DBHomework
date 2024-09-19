package dev.esternit.Books;

import io.swagger.v3.core.converter.AnnotatedType;
import io.swagger.v3.core.converter.ModelConverters;
import io.swagger.v3.core.converter.ResolvedSchema;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.context.annotation.Bean;

@OpenAPIDefinition(
        info = @Info(
                title = "Book JSON Repository Api",
                description = "Book System", version = "1.0.0",
                contact = @Contact(
                        name = "Dmitry Karpov",
                        email = "some@mail",
                        url = "https://algo-six.vercel.app/"
                )
        )
)
public class OpenApiConfig {
}
