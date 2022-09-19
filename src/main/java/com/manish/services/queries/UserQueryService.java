package com.manish.services.queries;

import com.manish.domains.User;
import com.manish.repositories.UserRepository;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.function.Supplier;

@Service
public class UserQueryService {
    private final UserRepository userRepository;
    private final MessageSource messageSource;

    public UserQueryService(UserRepository userRepository,
                            MessageSource messageSource) {
        this.userRepository = userRepository;
        this.messageSource = messageSource;
    }

    public User findById(final Long id) {
        return userRepository.findById(id).orElseThrow(userNotFoundSupplier());
    }

    private Supplier<RuntimeException> userNotFoundSupplier() {
        return () -> {
            String defaultMessage = "User Not Found";
            Locale locale = LocaleContextHolder.getLocale();
            String message = messageSource.getMessage("user.not.found", new Object[]{}, defaultMessage, locale);
            return new RuntimeException(message);
        };
    }
}
