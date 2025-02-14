package com.manish;

import com.manish.dtos.requests.Message;
import jakarta.annotation.PostConstruct;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;

import java.util.List;
import java.util.Set;

@SpringBootApplication
@RequiredArgsConstructor
@Slf4j
public class Application {
	private final Validator validator;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

//	@EventListener(ApplicationStartedEvent.class)
	void testingThatValidationWorkOnListOrNot() {
		log.info("Application started");
		log.info("Testing validation for Message DTO");

		List<Message> messages = List.of(
			new Message("Hello, World!"),
			new Message(""),
			new Message(null)
		);

//     Passing the list isn't working, so I'm commenting it out
//		Set<ConstraintViolation<List<Message>>> validate = validator.validate(messages);
//		for (ConstraintViolation<List<Message>> violation : validate) {
//			log.error("Validation error: {}", violation.getMessage());
//		}

		for (Message message : messages) {
			Set<ConstraintViolation<Message>> violations = validator.validate(message);
			if (CollectionUtils.isNotEmpty(violations)) {
				for (ConstraintViolation<Message> violation : violations) {
					log.error("Validation error: {}", violation.getMessage());
				}
			}
		}

	}
}
